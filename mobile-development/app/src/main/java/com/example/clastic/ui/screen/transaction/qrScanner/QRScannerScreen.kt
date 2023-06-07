package com.example.clastic.ui.screen.transaction.qrScanner

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.clastic.R
import com.example.clastic.ui.screen.transaction.components.QRCamera
import com.example.clastic.ui.screen.transaction.components.TransactionTopBar
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun QRScannerScreen(
    onScanned: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    var scannedString by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TransactionTopBar(stringId = R.string.scan_qr_code)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.scan_qr_code_to_start),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            if (hasCameraPermission) {
                QRCamera(
                    modifier = Modifier
                        .size(400.dp)
                        .padding(vertical = 20.dp),
                    onQRCodeScanned = { result ->
                    scannedString = result
                })
            }
            Text(
                text = "Text: $scannedString",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun QRScannerScreenPreview() {
    ClasticTheme {
        QRScannerScreen(onScanned = {})
    }
}