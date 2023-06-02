package com.example.clastic.data

import com.example.clastic.data.entity.Article
import com.example.clastic.data.entity.User
import com.example.clastic.data.network.Dao
import com.example.clastic.ui.screen.authentication.components.RegisterResult

class Repository(private val dao:Dao) {
    fun getArticleList(callback: (List<Article>?, Exception?) -> Unit){
        dao.getArticleList(callback)
    }

    fun getLoggedInUser(callback: (User?, Exception?) -> Unit) {
        dao.getLoggedInUser(callback)
    }

    suspend fun registerEmailPass(callback: (RegisterResult) -> Unit, name: String, email: String, password: String) {
        dao.registerEmailPass(
            name = name,
            email = email,
            password = password,
        )
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