package com.example.clastic.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberSmartRecord
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.ui.screen.profile.components.LogoutButton
import com.example.clastic.ui.screen.profile.components.ProfileCard
import com.example.clastic.ui.screen.profile.components.ProfileMenu
import com.example.clastic.ui.screen.profile.components.ProfileSummary
import com.example.clastic.ui.screen.profile.components.ProfileTopBar
import com.example.clastic.ui.theme.ClasticTheme

@Composable
fun ProfileScreen(
    onlogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackClick = { TODO() }
            )
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            // TODO(Change Static Data)
            ProfileCard(
                name = "Liefran",
                email = "liefran@gmail.com",
                points = "2.000",
                profileImage = painterResource(R.drawable.logo_botol_biru),
                modifier = modifier
                    .padding(vertical = 20.dp)
            )
            ProfileSummary(
                totalTransaction = "14",
                totalPlastic = "6.5",
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            ProfileMenu(
                title = stringResource(R.string.transaction_history),
                icon = Icons.Default.List
            )
            ProfileMenu(
                title = stringResource(R.string.redeem_history),
                icon = Icons.Default.FiberSmartRecord
            )
            ProfileMenu(
                title = stringResource(R.string.share_clastic),
                icon = Icons.Default.Share
            )
            ProfileMenu(
                title = stringResource(R.string.settings),
                icon = Icons.Default.Settings,
                modifier = Modifier
                    .drawBehind {
                        val borderSize = 1.dp.toPx()
                        drawLine(
                            color = Color(R.color.cyan_primary),
                            start = Offset(0f, size.height+2f),
                            end = Offset(size.width, size.height+2f),
                            strokeWidth = borderSize/2
                        )
                    }
            )
            LogoutButton(
                onClick = onlogout,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ClasticTheme {
        ProfileScreen(onlogout = {})
    }
}