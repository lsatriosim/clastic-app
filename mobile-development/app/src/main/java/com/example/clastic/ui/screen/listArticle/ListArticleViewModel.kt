package com.example.clastic.ui.screen.listArticle

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListArticleViewModel(private val repository: Repository):ViewModel() {
    private val _articleList = MutableStateFlow<List<Article>>(emptyList())
    val articleList : StateFlow<List<Article>> get() = _articleList

    init {
        fetchArticleList{articleList, exception ->
            _articleList.value = articleList?.sortedBy { it.title } ?: emptyList()
        }
    }

    fun fetchArticleList(callback: (List<Article>?, Exception?) -> Unit) {
        repository.getArticleList { list, exception ->
            if (exception != null) {
                Log.d("fetchDrakorList", "Fetching failed ${exception.message.toString()}")
            } else {
                Log.d("fetchDrakorList", "Fetching Success")
            }
            callback(list, exception) // Invoke the callback with the fetched list and exception
        }
    }
}