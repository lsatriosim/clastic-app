package com.example.clastic.ui.screen.dropPointMap

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.DropPoint
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DropPointMapViewModel(private val repository: Repository): ViewModel() {

    private val _dropPointList = MutableStateFlow<List<DropPoint>>(emptyList())
    val dropPointList : StateFlow<List<DropPoint>> get() = _dropPointList

    init {
        fetchDropPointList { dropPointList, _ ->
            _dropPointList.value = dropPointList?.sortedBy { it.id } ?: emptyList()
        }
    }

    fun openGoogleMaps(context: Context, address: String) {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val encodedAddress = Uri.encode(address)
            val mapsWebsiteUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedAddress")
            val mapsWebsiteIntent = Intent(Intent.ACTION_VIEW, mapsWebsiteUri)
            context.startActivity(mapsWebsiteIntent)
        }
    }

    private fun fetchDropPointList(callback: (List<DropPoint>?, Exception?) -> Unit) {
        repository.getDropPointList { list, exception ->
            if (exception != null) {
                Log.d("fetchDropPointList", "Fetching failed ${exception.message.toString()}")
            } else {
                Log.d("fetchDropPointList", "Fetching Success")
            }
            callback(list, exception)
        }
    }
}