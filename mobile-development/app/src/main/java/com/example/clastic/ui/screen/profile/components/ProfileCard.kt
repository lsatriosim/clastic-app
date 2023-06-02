package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileCard(
    name: String,
    email: String,
    profileImage: Painter,
    points: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(R.color.cyan_textfield),
                        colorResource(R.color.cyan_primary),
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .size(100.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = profileImage,
                    contentDescription = stringResource(R.string.profile_picture),
                    modifier = Modifier
                )
            }
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
            Text(
                text = email,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            // TODO(ADD POINTS VALUE)
            PointsButton(
                points = points,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ClasticTheme {
        ProfileCard(
            profileImage = painterResource(R.drawable.logo_botol_biru),
            name = "Liefran",
            email = "liefran@gmail.com",
            points = "2.000"
        )
    }
}