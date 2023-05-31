package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun LogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val red = Color.Red
    val logout = stringResource(R.string.logout)
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(13.dp),
        border = BorderStroke(2.dp, red),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            imageVector = Icons.Default.PowerSettingsNew,
            contentDescription = logout,
            tint = red,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Text(
            text = logout,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = red,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutButtonPreview() {
    ClasticTheme {
        LogoutButton(
            onClick = {}
        )
    }
}