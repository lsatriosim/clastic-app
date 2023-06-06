package com.example.clastic.ui.screen.profile.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.profile),
                color = Color.White
            )
        },
        backgroundColor = colorResource(R.color.cyan_primary)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileTopBarPreview(){
    ClasticTheme {
        ProfileTopBar()
    }
}