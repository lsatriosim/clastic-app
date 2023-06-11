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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.clastic.data.entity.Mission
import com.example.clastic.data.entity.MissionData
import com.example.clastic.data.entity.PlasticKnowledge
import com.example.clastic.data.entity.ProductData
import com.example.clastic.data.network.ApiConfig
import com.example.clastic.ui.screen.authentication.login.LoginScreen
import com.example.clastic.ui.screen.authentication.register.RegisterScreen
import com.example.clastic.ui.screen.classifier.CameraView
import com.example.clastic.ui.screen.classifier.ClassifierViewModel
import com.example.clastic.ui.screen.dropPointMap.DropPointMapScreen
import com.example.clastic.ui.screen.home.HomeScreen
import com.example.clastic.ui.screen.listArticle.ArticleScreen
import com.example.clastic.ui.screen.listArticle.ListArticleScreen
import com.example.clastic.ui.screen.mission.MissionDetailScreen
import com.example.clastic.ui.screen.mission.MissionListScreen
import com.example.clastic.ui.screen.myqrcode.MyQRCodeScreen
import com.example.clastic.ui.screen.productKnowledge.ProductKnowledgeScreen
import com.example.clastic.ui.screen.profile.ProfileScreen
import com.example.clastic.ui.screen.splashScreen.ClasticSplashScreen
import com.example.clastic.ui.screen.transaction.createTransaction.CreateTransactionScreen
import com.example.clastic.ui.screen.transaction.qrScanner.QRScannerScreen
import com.example.clastic.ui.screen.transaction.transactionCreated.TransactionCreatedScreen
import com.example.clastic.ui.screen.transactionHistory.transactionHistoryDetail.TransactionHistoryDetailScreen
import com.example.clastic.ui.screen.transactionHistory.transactionHistoryList.TransactionHistoryListScreen
import com.example.clastic.ui.screen.tutorial.TutorialScreen
import com.example.clastic.ui.theme.ClasticTheme
import com.example.clastic.uriToFile
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
                                    navHostController.navigate(Screen.Home.route)
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
                                    navHostController.navigate(Screen.Home.route)
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
                                    navHostController.navigate(Screen.Home.route)
                                },
                            )
                        }
                        composable(Screen.Home.route) {
                            HomeScreen(
                                onClick = { plasticTag ->
                                    navHostController.navigate(
                                        Screen.ProductKnowledge.createRoute(
                                            plasticTag
                                        )
                                    )
                                },
                                navController = navHostController,
                                navigateToQrCode = {
                                    navHostController.navigate(Screen.myQRCode.route)
                                },
                                tutorialScreen = {
                                    navHostController.navigate(Screen.tutorial.route)
                                },
                                onMissionClick = { missionTitle ->
                                    navHostController.navigate(
                                        Screen.missionDetail.createRoute(
                                            missionTitle
                                        )
                                    )
                                },
                                navigateToDropPointMap = {
                                    navHostController.navigate(Screen.dropPointMap.route)
                                },
                                navigateToQRCodeScanner = {
                                    navHostController.navigate(Screen.qrCodeScanner.route)
                                }
                            )
                        }
                        composable(Screen.tutorial.route) {
                            TutorialScreen()
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
                                navHostController = navHostController,
                                navigateToTransactionHistory = {
                                    navHostController.navigate(Screen.transactionHistory.route)
                                }
                            )
                        }
                        composable(Screen.myQRCode.route) {
                            MyQRCodeScreen(qrText = auth.currentUser?.uid.toString())
                        }
                        composable(Screen.qrCodeScanner.route) {
                            QRScannerScreen(
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.Home.route)
                                },
                                onScanned = { scannedUID ->
                                    navHostController.popBackStack()
                                    navHostController.navigate(
                                        Screen.createTransaction.createRoute(
                                            scannedUID
                                        )
                                    )
                                }
                            )
                        }
                        composable(
                            route = Screen.createTransaction.route,
                            arguments = listOf(navArgument("scannedUID") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val scannedUID = navBackStackEntry.arguments?.getString("scannedUID")
                            CreateTransactionScreen(
                                scannedUID = scannedUID,
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.Home.route)
                                },
                                navigateToTransactionCreated = { transactionId ->
                                    navHostController.popBackStack()
                                    navHostController.navigate(
                                        Screen.transactionCreated.createRoute(
                                            transactionId
                                        )
                                    )
                                }
                            )
                        }
                        composable(
                            route = Screen.transactionCreated.route,
                            arguments = listOf(navArgument("transactionId") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val transactionId =
                                navBackStackEntry.arguments?.getString("transactionId")
                            TransactionCreatedScreen(
                                transactionId = transactionId!!,
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.Home.route)
                                }
                            )
                        }
                        composable(Screen.missionList.route) {
                            MissionListScreen(onClick = { missionTitle ->
                                navHostController.navigate(
                                    Screen.missionDetail.createRoute(
                                        missionTitle
                                    )
                                )
                            }, navHostController = navHostController)
                        }
                        composable(
                            route = Screen.missionDetail.route,
                            arguments = listOf(navArgument("missionTitle") {
                                type = NavType.StringType
                            })
                        ) {
                            var mission: Mission? = null
                            for (missions in MissionData.dummyMission) {
                                if (missions.title.equals(it.arguments?.getString("missionTitle"))) {
                                    mission = missions
                                }
                            }
                            MissionDetailScreen(
                                mission = mission!!,
                                joinCampaign = { missionTitle ->

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
                                },
                                    bottomBar = {
                                        BottomBar(
                                            currentMenu = "Classify",
                                            navController = navHostController
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
                                                    Text(text = "Take from Camera",style = MaterialTheme.typography.subtitle1.copy(color = Color.White))
                                                }
                                                Button(
                                                    onClick = {
                                                        startGallery()
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color("#1FA4BB".toColorInt())
                                                    )
                                                ) {
                                                    Text(text = "Take From Gallery",style = MaterialTheme.typography.subtitle1.copy(color = Color.White))
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
                                                Text(text = "Clear Photo", style = MaterialTheme.typography.subtitle1.copy(color = Color.White))
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
                                                Text(text = "Submit",style = MaterialTheme.typography.subtitle1.copy(color = Color.White))
                                            }
                                            if (plasticTypeResult != "") {
                                                Text(
                                                    text = "Hasil : ",
                                                    style = MaterialTheme.typography.h5.copy(
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color("#1FA4BB".toColorInt())
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
                        composable(
                            route = Screen.transactionHistory.route,
                        ) {
                            TransactionHistoryListScreen(
                                navigateToProfile = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.profile.route)
                                },
                                navigateToDetail = { transactionId ->
                                    navHostController.navigate(
                                        Screen.transactionHistoryDetail.createRoute(
                                            transactionId
                                        )
                                    )
                                }
                            )
                        }
                        composable(
                            route = Screen.transactionHistoryDetail.route,
                            arguments = listOf(navArgument("transactionId") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val transactionId =
                                navBackStackEntry.arguments?.getString("transactionId")
                            TransactionHistoryDetailScreen(
                                transactionId = transactionId!!,
                                navigateToTransactionHistoryList = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.transactionHistory.route)
                                }
                            )
                        }
                        composable(Screen.dropPointMap.route) {
                            DropPointMapScreen(
                                navigateToHome = {
                                    navHostController.popBackStack()
                                    navHostController.navigate(Screen.Home.route)
                                }
                            )
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
