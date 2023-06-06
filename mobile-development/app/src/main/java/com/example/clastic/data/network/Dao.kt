package com.example.clastic.data.network

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.clastic.R
import com.example.clastic.data.entity.Article
import com.example.clastic.data.entity.User
import com.example.clastic.ui.screen.authentication.components.AuthenticationResult
import com.example.clastic.ui.screen.authentication.components.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.CancellationException

class Dao {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val storageRef = Firebase.storage.reference

    fun addDocuments(collectionName: String, item: Any) {
        db.collection(collectionName).add(item).addOnSuccessListener {
            Log.d("firebase: add", "item has been store succesfully with document id: ${it.id}")
        }.addOnFailureListener {
            Log.d("firebase: add", it.message.toString())
        }
    }

    fun deleteDocuments(collectionName: String, key: String, value: String) {
        db.collection(collectionName).whereEqualTo(key, value).limit(1).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection(collectionName).document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d("firebase: delete", "the document has been deleted")
                        }.addOnFailureListener {
                            Log.d(
                                "firebase: delete",
                                "Can't delete the document: ${it.message.toString()}"
                            )
                        }
                }
            }.addOnFailureListener {
                Log.d("firebase:delete", "Can't find the document")
            }
    }

    fun getDocuments(collectionName: String): MutableList<Any>? {
        val listDocument: MutableList<Any>? = null
        db.collection(collectionName).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    listDocument?.add(document)
                }
                Log.d("firebase: get", "Documents found")
            }.addOnFailureListener {
                Log.d("firebase: get", "Documents are not found")
            }

        return listDocument
    }

    fun getPhotoUrl(imageName: String, callback: (String) -> Unit) {
        val imageRef = storageRef.child(imageName)

        imageRef.downloadUrl
            .addOnSuccessListener { uri ->
                val downloadUrl = uri.toString()
                Log.d("getPhotoUrl", "Image URL Accepted: $downloadUrl")
                callback(downloadUrl)
            }
            .addOnFailureListener { exception ->
                Log.d("getPhotoUrl", "Failed to get image URL: ${exception.message.toString()}")
                callback("")
            }
    }

    fun getArticleList(callback: (List<Article>?, Exception?) -> Unit){
        val articleList = mutableListOf<Article>()

        db.collection("article").get()
            .addOnSuccessListener { documents ->
                Log.d("fetchArticleList", "Document Found")
                for(document in documents){
                    articleList.add(document.toObject(Article::class.java))
                }
                for (article in articleList){
                    Log.d("fetchDrakorList", article.toString())
                }
                callback(articleList, null)
            }.addOnFailureListener { exception ->
                Log.d("fetchArticleList", "Document not found: ${exception.message.toString()}")
                callback(null, exception)
            }
    }

    suspend fun login(context: Context, oneTapClient: SignInClient): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildLoginRequest(context)
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }
    private fun buildLoginRequest(context: Context): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
    suspend fun registerEmailPass(name: String, email: String, password: String): AuthenticationResult {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user
            Log.d("TOKEN: ", user?.getIdToken(false).toString())
            createRegisterResultSuccess(user, name)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createAuthenticationResultFailed(e.message)
        }
    }

    suspend fun loginEmailPass(email: String, password: String): AuthenticationResult? {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            Log.d("TOKEN: ", user?.getIdToken(false).toString())
            createLoginResultSuccess(user)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createAuthenticationResultFailed(e.message)
        }
    }

    suspend fun loginWithIntent(intent: Intent, oneTapClient: SignInClient): AuthenticationResult? {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            val documentSnapshot = db.collection("user").document(user?.email!!).get().await()

            if (documentSnapshot.exists()) {
                Log.d("resultLogOneTap", user.toString())
                createLoginResultSuccess(user)
            } else {
                createRegisterResultSuccess(user, user.email!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            createAuthenticationResultFailed(e.message)
        }
    }

    private fun createRegisterResultSuccess(user: FirebaseUser?, name: String): AuthenticationResult {
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
                    Log.d("RegisterResult", "$name's account has been made")
                }.addOnFailureListener { e ->
                    Log.w("RegisterResult", "Error adding document", e)
                }

        }

        return AuthenticationResult(
            data = user?.run {
                User(
                    userId = uid,
                    username = name,
                    email = email!!,
                    userPhoto = photoUrl?.toString(),
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

    private suspend fun createLoginResultSuccess(user: FirebaseUser?): AuthenticationResult? {
        val deferred = CompletableDeferred<AuthenticationResult?>()

        db.collection("user").document(user?.email!!).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val result = AuthenticationResult(
                        data = user.run {
                            User(
                                userId = uid,
                                email = email!!,
                                username = document.getString("username"),
                                coin = document.getLong("coin")?.toInt() ?: 0,
                                userPhoto = document.getString("userPhoto"),
                                level = document.getLong("level")?.toInt() ?: 1,
                                exp = document.getLong("exp")?.toInt() ?: 0,
                                createdAt = document.getString("createdAt") ?: "-",
                                role = document.getString("role") ?: "user",
                            )
                        },
                        errorMessage = null
                    )
                    deferred.complete(result)
                    Log.d("fetchUser", "User Found")
                } else {
                    deferred.complete(null)
                    Log.d("fetchUser", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("fetchUser", "Data Fetch failed with ", exception)
                deferred.completeExceptionally(exception)
            }

        return deferred.await()
    }

    private fun createAuthenticationResultFailed(message: String?): AuthenticationResult {
        return AuthenticationResult(
            data = null,
            errorMessage = message
        )
    }

    fun getLoggedInUserData(callback: (User?, Exception?) -> Unit) {
        var user: User? = null
        val currentUser = auth.currentUser
        db.collection("user").document(currentUser?.email!!).get()
            .addOnSuccessListener { documentsSnapshot ->
                if (documentsSnapshot.exists()) {
                    user = currentUser.run {
                        User(
                            userId = currentUser.uid,
                            email = currentUser.email!!,
                            username = documentsSnapshot.getString("username"),
                            coin = documentsSnapshot.getLong("coin")?.toInt() ?: 0,
                            userPhoto = documentsSnapshot.getString("userPhoto"),
                            level = documentsSnapshot.getLong("level")?.toInt() ?: 1,
                            exp = documentsSnapshot.getLong("exp")?.toInt() ?: 0,
                            createdAt = documentsSnapshot.getString("createdAt") ?: "-",
                            role = documentsSnapshot.getString("role") ?: "user",
                        )
                    }
                }
                callback(user, null)
                Log.d("fetchUser", "User Found")
            }.addOnFailureListener { exception ->
                callback(null, exception)
                Log.d("fetchUser", "User not found: ${exception.message.toString()}")
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
    suspend fun logout(oneTapClient: SignInClient){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}