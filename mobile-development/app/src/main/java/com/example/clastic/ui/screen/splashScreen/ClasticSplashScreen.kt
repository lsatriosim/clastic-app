package com.example.clastic.ui.screen.splashScreen

import android.net.Uri
import android.os.Looper
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clastic.R
import com.example.clastic.ui.screen.ViewModelFactory

@Composable
fun ClasticSplashScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val viewModel: SplashScreenViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    )
    var splashVisible by rememberSaveable { mutableStateOf(true) }
    if (splashVisible) {
        val videoUri = "android.resource://com.example.clastic/${R.raw.clastic_splash_screen}"
        AndroidView(
            factory = {innerContext ->
                VideoView(innerContext).apply {
                    setZOrderOnTop(true)
                    setVideoURI(Uri.parse(videoUri))
                    setOnPreparedListener { mediaPlayer ->
                        mediaPlayer.isLooping = false
                        mediaPlayer.start()

                        android.os.Handler(Looper.getMainLooper()).postDelayed({
                            mediaPlayer.stop()
                            splashVisible = false
                            if (viewModel.getLoggedInUser() != null) {
                                navigateToHome()
                            } else {
                                navigateToLogin()
                            }
                        }, 2000)
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}