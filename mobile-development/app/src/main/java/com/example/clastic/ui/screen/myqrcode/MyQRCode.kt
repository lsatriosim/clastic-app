package com.example.clastic.ui.screen.myqrcode

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun MyQRCodeScreen(qrText: String){
    generateQRCode(qrText, 256)
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
            Image(
                bitmap = imageBitmap,
                contentDescription = "QR Code",
                modifier = Modifier.fillMaxSize()
            )
        }
}