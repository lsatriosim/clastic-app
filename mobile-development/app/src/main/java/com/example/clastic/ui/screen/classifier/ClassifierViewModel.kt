package com.example.clastic.ui.screen.classifier

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.Article
import com.example.clastic.data.entity.Response
import com.example.clastic.data.network.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import java.io.File

class ClassifierViewModel(private val repository: Repository): ViewModel() {
    private val _result  = MutableLiveData<Response>()
    val result: LiveData<Response> = _result

    private val _plasticType = MutableStateFlow<String>("")
    val plasticType : StateFlow<String> get() = _plasticType

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading : StateFlow<Boolean> get() = _isLoading

    fun predict(url: String){
        val client = ApiConfig.getApiService().predict(url = url)
        client.enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ){
                _isLoading.value = false
                if(response.isSuccessful){
                    Log.d("testNetwork", "onSuccess: ${response.body().toString()}")
                    _result.value = response.body()
                    _plasticType.value = result.value?.prediction ?: "Tidak Diketahui"
                }else {
                    _plasticType.value = "Gagal mendapatkan hasil. Coba lagi!"
                    Log.e("testNetwork", "onFailedS: ${response.message()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<com.example.clastic.data.entity.Response>, t: Throwable) {
                _isLoading.value = false
                _plasticType.value = "Gagal menghubungi ke server. Coba lagi!"
                Log.e("testNetwork", "onFailed: ${t.message.toString()}")
            }
        })
    }

    fun addPhoto(file: File){
        _isLoading.value = true
        repository.uploadPhoto(file){photoUrl, exception ->
            getPhoto(file)
        }
    }

    fun getPhoto(file:File){
        Log.d("testUpload", "getting photo...")
        repository.getPhotoUrl(file){photoUrl, exception->
            Log.d("testUpload",  "photoURl: ${photoUrl!!}")
            predict(photoUrl!!)
        }
    }

    fun clearPredict(){
        _plasticType.value = ""
    }
}