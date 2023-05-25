package com.example.clastic.data

import com.example.clastic.data.entity.Article
import com.example.clastic.data.network.Dao

class Repository(private val dao:Dao) {
    fun getArticleList(callback: (List<Article>?, Exception?) -> Unit){
        dao.getArticleList(callback)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            dao: Dao
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(dao)
            }.also { instance = it }
    }
}