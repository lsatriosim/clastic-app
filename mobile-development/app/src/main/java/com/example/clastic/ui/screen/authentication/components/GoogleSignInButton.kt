package com.example.clastic.ui.screen.authentication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    stringId: Int,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(19.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_google_logo
            ),
            contentDescription = null
        )
        Text(
            text = stringResource(stringId),
            modifier = Modifier.padding(6.dp),
            color = Color.Gray,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInButtonPreview() {
    ClasticTheme {
        GoogleSignInButton(onClick = {}, stringId = R.string.sign_in_with_google)
    }
}