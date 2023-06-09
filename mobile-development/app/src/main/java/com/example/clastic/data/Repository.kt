package com.example.clastic.data

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.clastic.data.entity.Article
import com.example.clastic.data.entity.Transaction
import com.example.clastic.data.entity.User
import com.example.clastic.data.network.Dao
import com.example.clastic.ui.screen.authentication.components.AuthenticationResult
import com.example.clastic.ui.screen.authentication.components.UserData
import com.google.android.gms.auth.api.identity.SignInClient

class Repository(private val dao:Dao) {
    fun getArticleList(callback: (List<Article>?, Exception?) -> Unit){
        dao.getArticleList(callback)
    }

    suspend fun login(context: Context, oneTapClient: SignInClient): IntentSender? {
        return dao.login(context, oneTapClient)
    }

    suspend fun registerEmailPass(name: String, email: String, password: String): AuthenticationResult {
        return dao.registerEmailPass(
            name = name,
            email = email,
            password = password,
        )
    }

    suspend fun loginEmailPass(email: String, password: String): AuthenticationResult? {
        return dao.loginEmailPass(email, password)
    }

    suspend fun loginWithIntent(intent: Intent, oneTapClient: SignInClient): AuthenticationResult? {
        return dao.loginWithIntent(intent, oneTapClient)
    }

    fun getLoggedInUserData(callback: (User?, Exception?) -> Unit) {
        dao.getLoggedInUserData(callback)
    }

    fun getLoggedInUser(): UserData? {
        return dao.getLoggedInUser()
    }

    suspend fun logout(oneTapClient: SignInClient) {
        dao.logout(oneTapClient)
    }

    suspend fun isUserExist(uid: String): Boolean {
        return dao.isUserExist(uid)
    }

    suspend fun getNameByUid(uid: String): String {
        return dao.getNameByUid(uid)
    }

    suspend fun createTransaction(transactionResult: Transaction): String {
        return dao.createTransaction(transactionResult)
    }

    suspend fun getDropPointName(): String {
        return dao.getDropPointName()
    }

    suspend fun getTransactionById(id: String): Transaction {
        return dao.getTransactionById(id)
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