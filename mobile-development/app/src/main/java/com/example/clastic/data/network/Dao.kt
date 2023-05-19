package com.example.clastic.data.network

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Dao {
    private val db = Firebase.firestore
    private val storage = Firebase.storage.reference

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

    fun getImageUrlByName(imageName: String): String? {
        var downloadUrl: String? = null
        val imageRef = storage.child("images/${imageName}")
        imageRef.downloadUrl.addOnSuccessListener {
            downloadUrl = it.toString()
            Log.d("firebase: getImageUrl", "Image url has found: ${downloadUrl}")
        }.addOnFailureListener {
            Log.d("firebase: getImageUrl", "Image has not found: ${it.message.toString()}")
        }
        return downloadUrl
    }
}