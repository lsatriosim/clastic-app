package com.example.clastic.ui.screen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clastic.R
import com.example.clastic.ui.screen.authentication.components.GoogleAuthUiClient
import com.example.clastic.ui.screen.authentication.login.LoginScreen
import com.example.clastic.ui.screen.authentication.login.LoginViewModel
import com.example.clastic.ui.screen.authentication.register.RegisterScreen
import com.example.clastic.ui.screen.authentication.register.RegisterViewModel
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.Product
import com.example.clastic.data.entity.ProductData
import com.example.clastic.ui.screen.home.HomeScreen
import com.example.clastic.ui.screen.home.ProductKnowledgeComponent
import com.example.clastic.ui.screen.listArticle.ArticleScreen
import com.example.clastic.ui.screen.listArticle.ListArticleScreen
import com.example.clastic.ui.screen.productKnowledge.ProductKnowledgeScreen
import com.example.clastic.ui.theme.ClasticTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

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
                    var splashVisible by remember { mutableStateOf(true) }
                    if (splashVisible) {
                        ClasticSplashScreen(onSplashFinished = { splashVisible = false })
                    } else {
                        var startDestination = Screen.login.route
                        if (googleAuthUiClient.getLoggedInUser() != null) {
                            startDestination = Screen.articleList.route
                        }

                        NavHost(
                            navController = navHostController,
                            startDestination = startDestination
                        ) {
                            composable(Screen.login.route) {
                                val viewModel = viewModel<LoginViewModel>()
                                val state by viewModel.state.collectAsState()

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val loginResult = googleAuthUiClient.loginWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onLoginResult(loginResult)
                                            }
                                        }
                                    }
                                )
                                LaunchedEffect(key1 = state.isLoginSuccessful) {
                                    if (state.isLoginSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Login Success",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navHostController.navigate(Screen.articleList.route)
                                        viewModel.resetState()
                                    }
                                }

                                LoginScreen(
                                    state = state,
                                    navigateToRegister = {
                                        navHostController.popBackStack()
                                        navHostController.navigate(Screen.register.route)
                                    },
                                    onLoginClick = {
                                        lifecycleScope.launch {
                                            val loginIntentSender = googleAuthUiClient.login()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    loginIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    },
                                    googleAuthUiClient = googleAuthUiClient,
                                    viewModel = viewModel
                                )
                            }
                            composable(Screen.register.route) {
                                val viewModel = viewModel<RegisterViewModel>()
                                val state by viewModel.state.collectAsState()

                                LaunchedEffect(key1 = state.isLoginSuccessful) {
                                    if (state.isLoginSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Register Success",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navHostController.navigate(Screen.articleList.route)
                                        viewModel.resetState()
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val loginResult = googleAuthUiClient.loginWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onRegisterResult(loginResult)
                                            }
                                        }
                                    }
                                )

                                RegisterScreen(
                                    navigateToLogin = {
                                        navHostController.popBackStack()
                                        navHostController.navigate(Screen.login.route)
                                    },
                                    onRegisterClick = {
                                        lifecycleScope.launch {
                                            val loginIntentSender = googleAuthUiClient.login()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    loginIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    },
                                    viewModel = viewModel,
                                    googleAuthUiClient = googleAuthUiClient
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
                                    URLDecoder.decode(navBackStackEntry.arguments?.getString("articleUrl"))
                                Log.d("arguments", articleUrl.toString())
                                ArticleScreen(contentUrl = articleUrl)
                            }
                        }
                    }
                }
                composable(
                    route = Screen.ProductKnowledge.route,
                    arguments = listOf(navArgument("tag"){type = NavType.StringType})
                ){navBackStackEntry ->
                    var plasticType: PlasticKnowledge? = null
                    for(plastic in ProductData.plasticTypes){
                        if(plastic.tag.equals(navBackStackEntry.arguments?.getString("tag"))){
                            plasticType = plastic
                        }
                    }
                    Log.d("productKnowledge", plasticType.toString())
                    ProductKnowledgeScreen(plasticType = plasticType!!)
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