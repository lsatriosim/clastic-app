package com.example.clastic.ui.screen.authentication.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.clastic.R

@Composable
fun AuthenticationMethodDivider(
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(bottom = 12.dp)
    ) {
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = modifier
                .weight(4f)
        )
        Text(
            text = stringResource(R.string.or),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .weight(1f)
        )
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = modifier
                .weight(4f)
        )
    }
}
