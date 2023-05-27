package com.example.clastic.ui.screen.authentication.components

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.clastic.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun login(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildLoginRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    fun loginEmailPass(email: String, password: String): LoginResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email,password).result.user
            Log.d("TOKEN: ", user?.getIdToken(false).toString())
            LoginResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        userImage = photoUrl?.toString(),
                        token = getIdToken(false).toString(),
                        // sus token input
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            LoginResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun loginWithIntent(intent: Intent): LoginResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            LoginResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        userImage = photoUrl?.toString(),
                        token = googleIdToken
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            LoginResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun logout(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getLoggedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            userImage = photoUrl?.toString(),
            token = getIdToken(false).toString()
        )
    }

    private fun buildLoginRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}