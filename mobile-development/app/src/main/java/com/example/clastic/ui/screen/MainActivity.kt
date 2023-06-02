package com.example.clastic.ui.screen

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.authentication.login.LoginScreen
import com.example.clastic.ui.screen.authentication.register.RegisterScreen
import com.example.clastic.ui.screen.listArticle.ArticleScreen
import com.example.clastic.ui.screen.listArticle.ListArticleScreen
import com.example.clastic.ui.screen.productKnowledge.ProductKnowledgeScreen
import com.example.clastic.ui.screen.profile.ProfileScreen
import com.example.clastic.ui.screen.splashScreen.ClasticSplashScreen
import com.example.clastic.ui.screen.splashScreen.SplashScreenViewModel
import com.example.clastic.ui.theme.ClasticTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClasticTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    val navHostController: NavHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.splashScreen.route
                    ) {
                        composable(Screen.splashScreen.route) {
                            var splashVisible by rememberSaveable { mutableStateOf(true) }
                            val viewModel: SplashScreenViewModel = viewModel(
                                factory = ViewModelFactory.getInstance(
                                    LocalContext.current
                                )
                            )
                            if (splashVisible) {
                                ClasticSplashScreen(
                                    viewModel = viewModel,
                                    onSplashFinished = {
                                        splashVisible = false
                                        navHostController.popBackStack()
                                        if (viewModel.getLoggedInUser() != null) {
                                            navHostController.navigate(Screen.profile.route)
                                        } else {
                                            navHostController.navigate(Screen.login.route)
                                        }
                                    }
                                )
                            }
                        }
                        composable(Screen.login.route) {
                            LoginScreen(
                                navigateToRegister = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.register.route)
                                },
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    // TODO(change navigation here for debug)
                                    navHostController.navigate(Screen.profile.route)
                                }
                            )
                        }
                        composable(Screen.register.route) {
                            RegisterScreen(
                                navigateToLogin = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.login.route)
                                },
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    // TODO(change navigation here for debug)
                                    navHostController.navigate(Screen.profile.route)
                                },
                            )
                        }
                        composable(Screen.articleList.route) {
                            ListArticleScreen(onClick = { articleUrl ->
                                val encodeArticleUrl = URLEncoder.encode(articleUrl, StandardCharsets.UTF_8.toString())
                                navHostController.navigate(Screen.articleDetail.createRoute(encodeArticleUrl))
                            })
                        }
                        composable(
                            route = Screen.articleDetail.route,
                            arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
                        ) { navBackStackEntry ->
                            val articleUrl =
                                URLDecoder.decode(navBackStackEntry.arguments?.getString("articleUrl"), "UTF-8")
                            Log.d("arguments", articleUrl.toString())
                            ArticleScreen(contentUrl = articleUrl)
                        }
                        composable(
                            route = Screen.ProductKnowledge.route,
                            arguments = listOf(navArgument("tag"){type = NavType.StringType})
                        ){navBackStackEntry ->
                            var plasticType: PlasticKnowledge? = null
                            for(plastic in ProductData.plasticTypes){
                                if(plastic.tag == navBackStackEntry.arguments?.getString("tag")){
                                    plasticType = plastic
                                }
                            }
                            Log.d("productKnowledge", plasticType.toString())
                            ProductKnowledgeScreen(plasticType = plasticType!!)
                        }
                        composable(Screen.profile.route) {
                            ProfileScreen(onLogout = {
                                navHostController.popBackStack()
                                navHostController.navigate(Screen.login.route)
                            })
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun MainContent() {
    val db = Firebase.firestore
    val storage = Firebase.storage.reference

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
            }.addOnFailureListener { e ->
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