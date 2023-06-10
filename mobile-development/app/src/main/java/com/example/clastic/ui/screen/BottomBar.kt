package com.example.clastic.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.clastic.R
import com.example.clastic.data.entity.BottomBarItem

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavController = NavController(LocalContext.current),
    currentMenu: String
) {
    BottomNavigation(
        modifier = modifier,
        contentColor = Color("#0097B2".toColorInt()),
        backgroundColor = Color("#FFFFFF".toColorInt())
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = "Home",
                icon = ImageVector.vectorResource(R.drawable.ic_home)
            ),
            BottomBarItem(
                title = "Article",
                icon = ImageVector.vectorResource(id = R.drawable.ic_article)
            ),
            BottomBarItem(
                title = "Classify",
                icon = ImageVector.vectorResource(id = R.drawable.baselic_camera)
            ),
            BottomBarItem(
                title = "Mission",
                icon = ImageVector.vectorResource(id = R.drawable.ic_campaign_white)
            ),
            BottomBarItem(
                title = "Profile",
                icon = ImageVector.vectorResource(id = R.drawable.ic_person_white)
            )
        )
        navigationItems.map {
            BottomNavigationItem(modifier = Modifier,
                selected = it.title == currentMenu,
                onClick = {
                    if (it.title != currentMenu) {
                        when (it.title) {
                            "Home" -> {
                                navController.popBackStack()
                                navController.navigate(Screen.Home.route)}
                            "Article" -> {
                                navController.popBackStack()
                                navController.navigate(Screen.articleList.route)
                            }
                            "Profile" -> {
                                navController.popBackStack()
                                navController.navigate(Screen.profile.route)
                            }
                            "Classify" -> {
                                navController.popBackStack()
                                navController.navigate(Screen.classifier.route)
                            }
                            "Mission" -> {
                                navController.popBackStack()
                                navController.navigate(Screen.missionList.route)
                            }
                        }
                    }
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = { Text(text = it.title) })
        }
    }

}