package com.example.clastic.ui.screen.dropPointMap

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clastic.ui.screen.ViewModelFactory
import com.example.clastic.ui.theme.ClasticTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DropPointMapScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewModel: DropPointMapViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context
        )
    )
    val dropPointList by viewModel.dropPointList.collectAsState()
    val cameraPositionState = rememberCameraPositionState {

    }

    Column(
        modifier = modifier
    ) {
        DropPointMapTopBar(
            navigateToHome = navigateToHome
        )
        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier
        ) {
            for(dropPoint in dropPointList) {
                val boundBuilder = LatLngBounds.Builder()
                val position = LatLng(dropPoint.lat, dropPoint.long)
                boundBuilder.include(position)
                Marker(
                    state = MarkerState(position = position),
                    title = dropPoint.name,
                    snippet = dropPoint.location,
                    onInfoWindowClick = {
                        viewModel.openGoogleMaps(context, dropPoint.location)
                    }
                )
                val bounds = boundBuilder.build()
                val boundsCenter = LatLng(
                    (bounds.southwest.latitude + bounds.northeast.latitude) / 2,
                    (bounds.southwest.longitude + bounds.northeast.longitude) / 2
                )
                cameraPositionState.position = CameraPosition.fromLatLngZoom(boundsCenter,11f)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropPointMapScreenPreview() {
    ClasticTheme {
        DropPointMapScreen(
            navigateToHome = {}
        )
    }
}