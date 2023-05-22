package com.example.clastic

import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Looper
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ClasticSplashScreen(onSplashFinished: () -> Unit) {
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
                        onSplashFinished()
                    }, 2000)
                }
                OnPreparedListener{

                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
    )
}