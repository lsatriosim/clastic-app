package com.example.clastic.ui.screen.transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun TransactionTopBar(
    navigateToHome: () -> Unit,
    stringId: Int
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(30.dp)
                    .clickable { navigateToHome() }
            )
        },
        title = {
            Text(
                text = stringResource(stringId),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        backgroundColor = colorResource(R.color.cyan_primary)
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionTopBarPreview(){
    ClasticTheme {
        TransactionTopBar(
            stringId = R.string.scan_qr_code,
            navigateToHome = {}
        )
    }
}