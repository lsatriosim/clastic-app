package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.profile),
                color = Color.White,
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        },
        backgroundColor = colorResource(R.color.cyan_primary),
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileTopBarPreview(){
    ClasticTheme {
        ProfileTopBar()
    }
}