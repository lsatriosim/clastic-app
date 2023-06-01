package com.example.clastic.ui.screen.authentication.components

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.clastic.R
import com.example.clastic.data.entity.User
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

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

    suspend fun registerEmailPass(name: String, email: String, password: String): RegisterResult {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user
            Log.d("TOKEN: ", user?.getIdToken(false).toString())
            createRegisterResultSuccess(user, name)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createRegisterResultFailed(e.message)
        }
    }

    suspend fun loginEmailPass(email: String, password: String): LoginResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            Log.d("TOKEN: ", user?.getIdToken(false).toString())
            createLoginResultSuccess(user)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createLoginResultFailed(e.message)
        }
    }

    suspend fun loginWithIntent(intent: Intent): LoginResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            createLoginResultSuccess(user)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createLoginResultFailed(e.message)
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

    private fun createLoginResultSuccess(user: FirebaseUser?): LoginResult {
        val docRef = db.collection("user").document(user?.email!!)
        var result: LoginResult = LoginResult(null, null)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    //TODO()
                    result = LoginResult(data = null, errorMessage = null)
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Data Fetch failed with ", exception)
            }
        return LoginResult(
            data = user.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    userImage = photoUrl?.toString(),
                    token = user.getIdToken(false).toString()
                )
            },
            errorMessage = null
        )
    }

    private fun createRegisterResultSuccess(user: FirebaseUser?, name: String): RegisterResult {
        val rawDate = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = df.format(rawDate)

        user?.run {
            val newUser = hashMapOf(
                "userId" to uid,
                "username" to name,
                "userPhoto" to photoUrl?.toString(),
                "coin" to 0,
                "createdAt" to currentDate,
                "level" to 1,
                "exp" to 0,
                "role" to "user"
            )
            db.collection("user").document(email!!).set(newUser)
                .addOnSuccessListener {
                    Log.d(TAG, "$name's account has been made")
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }

        return RegisterResult(
            data = user?.run {
                User(
                    userId = uid,
                    username = name,
                    email = email,
                    userPhoto = photoUrl?.toString(),
                    token = user.getIdToken(false).toString(),
                    coin = 0,
                    createdAt = currentDate,
                    level = 1,
                    exp = 0,
                    role = "user"
                )
            },
            errorMessage = null
        )
    }

    private fun createLoginResultFailed(message: String?): LoginResult {
        return LoginResult(
            data = null,
            errorMessage = message
        )
    }

    private fun createRegisterResultFailed(message: String?): RegisterResult {
        return RegisterResult(
            data = null,
            errorMessage = message
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

    companion object{
        private const val TAG = "Firestore"
    }
}