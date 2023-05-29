package com.example.clastic.data.network

import android.util.Log
import com.example.clastic.data.entity.Article
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Dao {
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

}