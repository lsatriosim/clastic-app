package com.example.clastic.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.clastic.R
import com.example.clastic.data.entity.MissionData
import com.example.clastic.ui.screen.mission.MissionCard

@Composable
fun MisiPlastikComponent(modifier: Modifier = Modifier, onMissionClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = "Misi Plastik",
                style = MaterialTheme.typography.h5.copy(color = Color.Black)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_campaign_white),
                contentDescription = null,
                tint = Color.Gray
            )
        }
        Text(
            text = "Ayo tukarkan sisa plastikmu menjadi point!!!",
            style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
        )

        //content section: Tukarkan Plastikmu
        MissionCard(mission =
        MissionData.dummyMission[0], onClick = onMissionClick)
    }
}
