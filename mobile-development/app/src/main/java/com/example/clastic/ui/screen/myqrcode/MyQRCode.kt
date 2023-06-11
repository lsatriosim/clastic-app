package com.example.clastic.ui.screen.myqrcode

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun MyQRCodeScreen(qrText: String){
    generateQRCode(qrText, 600)
}

@Composable
fun generateQRCode(text: String, size: Int){
    val bitmap = remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(text) {
        try {
            val writer = QRCodeWriter()
            val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size)
            val pixels = IntArray(size * size)
            for (y in 0 until size) {
                for (x in 0 until size) {
                    pixels[y * size + x] = if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
                }
            }
            val imageBitmap = Bitmap.createBitmap(pixels, size, size, Bitmap.Config.RGB_565)
            bitmap.value = imageBitmap.asImageBitmap()
        } catch (e: WriterException) {
            // Handle the exception appropriately
            e.printStackTrace()
        }
    }

    bitmap.value?.let { imageBitmap ->
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Text(text = "My QR Code", color = Color.White)
                    },
                    backgroundColor = Color("#1FA4BB".toColorInt())
                )
            })
            {innerPadding->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.White), contentAlignment = Alignment.Center){
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "QR Code",
                            modifier = Modifier
                        )
                        Text(text = "Show this QRCode to\nDrop Point Operator", style = MaterialTheme.typography.h5.copy(color = Color.Black, textAlign = TextAlign.Center))
                    }
                }
            }
        }
}