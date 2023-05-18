package com.example.clastic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clastic.ui.theme.ClasticTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClasticTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android", db)
                }
            }
        }
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