package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileMenuPlaceholder(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector
) {
    val cyanPrimary = colorResource(R.color.cyan_primary)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = cyanPrimary,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = borderSize
                )
                drawLine(
                    color = cyanPrimary,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize/2
                )
            }
            .clickable {  }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = cyanPrimary,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .size(40.dp)
        )
        Text(
            text = title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = cyanPrimary,
            modifier = Modifier
                .size(40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMenuPlaceholderPreview(){
    ClasticTheme {
        ProfileMenuPlaceholder(
            title = "Histori Transaksi Plastik",
            icon = Icons.Default.List,
        )
    }
}