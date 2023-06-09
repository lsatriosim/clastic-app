package com.example.clastic.ui.screen

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.example.clastic.R
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.Mission
import com.example.clastic.data.entity.MissionData
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.ProductData
import com.example.clastic.data.network.ApiConfig
import com.example.clastic.di.Injection
import com.example.clastic.ui.screen.authentication.login.LoginScreen
import com.example.clastic.ui.screen.authentication.register.RegisterScreen
import com.example.clastic.ui.screen.classifier.CameraView
import com.example.clastic.ui.screen.classifier.ClassifierScreen
import com.example.clastic.ui.screen.classifier.ClassifierViewModel
import com.example.clastic.ui.screen.home.HomeScreen
import com.example.clastic.ui.screen.listArticle.ArticleScreen
import com.example.clastic.ui.screen.listArticle.ListArticleScreen
import com.example.clastic.ui.screen.mission.MissionDetailScreen
import com.example.clastic.ui.screen.mission.MissionListScreen
import com.example.clastic.ui.screen.myqrcode.MyQRCodeScreen
import com.example.clastic.ui.screen.productKnowledge.ProductKnowledgeScreen
import com.example.clastic.ui.screen.profile.ProfileScreen
import com.example.clastic.ui.screen.splashScreen.ClasticSplashScreen
import com.example.clastic.ui.theme.ClasticTheme
import com.example.clastic.uriToFile
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    private val auth = Firebase.auth

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private var photoUri: MutableState<Uri> = mutableStateOf(Uri.EMPTY)
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                photoUri.value = uri
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("kilo", "Permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        ApiConfig.getApiService().predict("")

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
                            ClasticSplashScreen(
                                //TODO(Change navigation here for debug)
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.profile.route)
                                },
                                navigateToLogin = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.login.route)
                                },
                            )
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
                        composable(Screen.Home.route) {
                            HomeScreen(onClick = { plasticTag ->
                                navHostController.navigate(
                                    Screen.ProductKnowledge.createRoute(
                                        plasticTag
                                    )
                                )
                            }, navController = navHostController,
                                navigateToQrCode = {
                                    navHostController.navigate(Screen.myQRCode.route)
                                },
                            onMissionClick = {missionTitle ->
                                navHostController.navigate(Screen.missionDetail.createRoute(missionTitle))
                            })
                        }
                        composable(Screen.articleList.route) {
                            ListArticleScreen(
                                onClick = { articleUrl ->
                                    val encodeArticleUrl = URLEncoder.encode(
                                        articleUrl,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navHostController.navigate(
                                        Screen.articleDetail.createRoute(
                                            encodeArticleUrl
                                        )
                                    )
                                },
                                navController = navHostController
                            )
                        }
                        composable(
                            route = Screen.articleDetail.route,
                            arguments = listOf(navArgument("articleUrl") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val articleUrl =
                                URLDecoder.decode(
                                    navBackStackEntry.arguments?.getString("articleUrl"),
                                    "UTF-8"
                                )
                            Log.d("arguments", articleUrl.toString())
                            ArticleScreen(contentUrl = articleUrl)
                        }
                        composable(
                            route = Screen.ProductKnowledge.route,
                            arguments = listOf(navArgument("tag") { type = NavType.StringType })
                        ) { navBackStackEntry ->
                            var plasticType: PlasticKnowledge? = null
                            for (plastic in ProductData.plasticTypes) {
                                if (plastic.tag.equals(navBackStackEntry.arguments?.getString("tag"))) {
                                    plasticType = plastic
                                }
                            }
                            Log.d("productKnowledge", plasticType.toString())
                            ProductKnowledgeScreen(plasticType = plasticType!!)
                        }
                        composable(Screen.profile.route) {
                            ProfileScreen(
                                onLogout = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.login.route)
                                },
                                navHostController = navHostController
                            )
                        }
                        composable(Screen.myQRCode.route) {
                            MyQRCodeScreen(qrText = auth.currentUser?.uid.toString())
                        }
                        composable(Screen.missionList.route) {
                            MissionListScreen(onClick = {missionTitle ->
                                navHostController.navigate(Screen.missionDetail.createRoute(missionTitle))
                            })
                        }
                        composable(route = Screen.missionDetail.route,
                            arguments = listOf(navArgument("missionTitle") {
                                type = NavType.StringType
                            })
                        ) {
                            var mission: Mission? = null
                            for(missions in MissionData.dummyMission){
                                if(missions.title.equals(it.arguments?.getString("missionTitle"))){
                                    mission = missions
                                }
                            }
                            MissionDetailScreen(mission = mission!!, joinCampaign = {missionTitle ->

                            })
                        }
                        composable(Screen.classifier.route) {
                            val viewModel: ClassifierViewModel = viewModel(
                                factory = ViewModelFactory.getInstance(LocalContext.current)
                            )

                            val plasticTypeResult by viewModel.plasticType.collectAsState()
                            val isLoadingSubmit by viewModel.isLoading.collectAsState()
                            val showDialog = remember { mutableStateOf(false) }

                            Log.d("testNetwork", viewModel.result.toString())

                            if (shouldShowCamera.value) {
                                CameraView(
                                    outputDirectory = outputDirectory,
                                    executor = cameraExecutor,
                                    onImageCaptured = { uri ->
                                        handleImageCapture(uri)
                                    },
                                    onError = { Log.e("kilo", "View Error:", it) },
                                )
                            } else {
                                Scaffold(topBar = {
                                    TopAppBar(
                                        title = {
                                            Text(
                                                text = "Classify",
                                                color = Color.White
                                            )
                                        },
                                        backgroundColor = Color("#1FA4BB".toColorInt())
                                    )
                                }) { innerPadding ->
                                    if (showDialog.value) {
                                        Dialog(onDismissRequest = {
                                            showDialog.value = false
                                        }) {
                                            PopUpCard(closePopUp = { showDialog.value = false })
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp),
                                            contentAlignment = Alignment.TopEnd
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_question_white),
                                                contentDescription = null,
                                                tint = Color.Gray,
                                                modifier = Modifier.clickable {
                                                    showDialog.value = true
                                                })
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                        ) {
                                            if (photoUri.value != Uri.EMPTY) {
                                                Image(
                                                    painter = rememberImagePainter(data = photoUri.value),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(400.dp)
                                                        .padding(
                                                            horizontal = 32.dp,
                                                            vertical = 12.dp
                                                        )
                                                )
                                            } else {
                                                Image(
                                                    painter = painterResource(id = R.drawable.preview_photo),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(32.dp)
                                                )
                                            }
                                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                                Button(
                                                    onClick = {
                                                        shouldShowCamera.value = true
                                                        viewModel.clearPredict()
                                                        requestCameraPermission()
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color("#1FA4BB".toColorInt())
                                                    )
                                                ) {
                                                    Text(text = "Take from Camera")
                                                }
                                                Button(
                                                    onClick = {
                                                        startGallery()
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color("#1FA4BB".toColorInt())
                                                    )
                                                ) {
                                                    Text(text = "Take From Gallery")
                                                }
                                            }
                                            Button(
                                                onClick = {
                                                    viewModel.clearPredict()
                                                    photoUri.value = Uri.EMPTY
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color("#1FA4BB".toColorInt())
                                                )
                                            ) {
                                                Text(text = "Clear Photo")
                                            }
                                            Button(
                                                onClick = {
                                                    val photoFile = uriToFile(
                                                        photoUri.value,
                                                        this@MainActivity
                                                    )
                                                    Log.d(
                                                        "testNetwork",
                                                        photoFile.name.toString()
                                                    )
                                                    viewModel.addPhoto(photoFile)
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color("#1FA4BB".toColorInt())
                                                ),
                                                enabled = !isLoadingSubmit
                                            ) {
                                                Text(text = "Submit")
                                            }
                                            if (plasticTypeResult != "") {
                                                Text(
                                                    text = "Hasil : ",
                                                    style = MaterialTheme.typography.h5.copy(
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                )
                                                Text(
                                                    text = plasticTypeResult,
                                                    style = MaterialTheme.typography.subtitle1
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false

        photoUri.value = uri
        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
