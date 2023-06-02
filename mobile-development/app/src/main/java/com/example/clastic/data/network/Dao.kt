package com.example.clastic.data.network

import android.util.Log
import com.example.clastic.data.entity.Article
import com.example.clastic.data.entity.User
import com.example.clastic.ui.screen.authentication.components.GoogleAuthUiClient
import com.example.clastic.ui.screen.authentication.components.LoginResult
import com.example.clastic.ui.screen.authentication.components.RegisterResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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
                    Log.d("RegisterResult", "$name's account has been made")
                }.addOnFailureListener { e ->
                    Log.w("RegisterResult", "Error adding document", e)
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

    fun getLoggedInUser(callback: (User?, Exception?) -> Unit) {
        var user: User? = null
        val currentUser = auth.currentUser
        db.collection("user").document(currentUser?.email!!).get()
            .addOnSuccessListener { documentsSnapshot ->
                if (documentsSnapshot.exists()) {
                    user = currentUser.run {
                        User(
                            userId = currentUser.uid,
                            email = currentUser.email,
                            username = documentsSnapshot.getString("username"),
                            token = currentUser.getIdToken(false).toString(),
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

}