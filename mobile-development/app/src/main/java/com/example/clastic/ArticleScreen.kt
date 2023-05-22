package com.example.clastic

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ArticleScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl("https://indonesiabaik.id/infografis/indonesia-darurat-sampah-plastik")
                }
            },
            update = {
                it.loadUrl("https://indonesiabaik.id/infografis/indonesia-darurat-sampah-plastik")
            }
        )
    }
}