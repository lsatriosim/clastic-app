package com.example.clastic.ui.screen.profile.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberSmartRecord
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clastic.R
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun PointsButton(
    points: String,
    modifier: Modifier = Modifier
) {
    val cyanPrimaryVariant2 = colorResource(R.color.cyan_primary_variant2)

    Button(
        onClick = {},
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        contentPadding = PaddingValues(
            horizontal = 10.dp
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.FiberSmartRecord,
            tint = cyanPrimaryVariant2,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
        )
        Text(
            text = "$points pts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = cyanPrimaryVariant2,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = cyanPrimaryVariant2,
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PointsButtonPreview() {
    ClasticTheme {
        PointsButton(
            points = "2.000"
        )
    }
}