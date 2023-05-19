package com.example.clastic

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.clastic.ui.theme.ClasticTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClasticTheme {
                InitiateHomeScreen()
            }
        }
    }
}

@Composable
fun InitiateHomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        var splashVisible by remember { mutableStateOf(true) }
        if (splashVisible) {
            ClasticSplashScreen(onSplashFinished = { splashVisible = false })
        } else {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val db = Firebase.firestore
    val storage = Firebase.storage.reference

    val file = Uri.fromFile(File("drawable/article1"))
    val imageRef = storage.child("images/${file.lastPathSegment}")
    imageRef.putFile(file).addOnSuccessListener{
        Log.d("firebase: upload image", "Image has been upload")
    }.addOnFailureListener{
        Log.d("firebase: upload image", "Upload Failed")
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("Android", db)
    }
}

@Composable
fun Greeting(name: String, db: FirebaseFirestore) {
    Button(modifier = Modifier.wrapContentSize(), onClick = {
        val user = hashMapOf(
            "firstName" to name,
            "lastName" to "Abdul",
            "Age" to 21
        )
        db.collection("users").add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("testFirebase", "Document has been made with id : ${documentReference.id}")
            }.addOnFailureListener{e ->
                Log.w("testFirebase", "Error adding document", e)
            }
    }) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(db: FirebaseFirestore = Firebase.firestore) {
    ClasticTheme {
        Greeting("Android", db)
    }
}