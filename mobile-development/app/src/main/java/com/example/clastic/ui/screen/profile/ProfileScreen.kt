package com.example.clastic.ui.screen.profile

import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clastic.R
import com.example.clastic.ui.screen.BottomBar
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.screen.profile.components.LogoutButton
import com.example.clastic.ui.screen.profile.components.ProfileCard
import com.example.clastic.ui.screen.profile.components.ProfileMenu
import com.example.clastic.ui.screen.profile.components.ProfileSummary
import com.example.clastic.ui.screen.profile.components.ProfileTopBar
import com.example.clastic.ui.theme.ClasticTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.NumberFormat

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    navHostController:NavHostController
) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val mainScope = MainScope()
    val user by viewModel.user.collectAsState()


    Scaffold(
        topBar = {
            ProfileTopBar()
        },
        bottomBar = { BottomBar(currentMenu = "Profile", navController = navHostController)},
        modifier = modifier
            .background(Color.White),
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .background(Color.White),
        ) {
            // TODO(Change Static Picture)
            ProfileCard(
                name = user?.username ?: "-",
                email = user?.email ?: "-",
                points = if(user?.coin == null) "0" else NumberFormat.getInstance().format(user?.coin).toString(),
                profileImage = if(user?.userPhoto == null) painterResource(R.drawable.logo_botol_biru)
                else painterResource(R.drawable.logo_botol_biru),
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
            // TODO(Change Static Data)
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
                onClick = {
                    mainScope.launch {
                        viewModel.logout(context)
                    }
                    onLogout()
                },
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ProfileScreenPreview() {
    ClasticTheme {
        ProfileScreen(onLogout = {}, navHostController = NavHostController(LocalContext.current))
    }
}