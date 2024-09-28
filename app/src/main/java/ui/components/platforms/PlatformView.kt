package ui.components.platforms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import interfaces.NavigationInterface
import interfaces.PlatformInterface
import entity.enums.BottomBarDestination
import entity.enums.BottomBarState
import ui.components.navigation.BottomNavigationBar
import viewmodels.PlatformListViewModel

@Composable
fun PlatformView(platformInterface: PlatformInterface, navigationInterface: NavigationInterface, platformListViewModel: PlatformListViewModel) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

            BottomNavigationBar(
                items = BottomBarDestination.entries,
                current = BottomBarState.HOME,
                navigationInterface = navigationInterface
            )

        }
    ) { innerPadding ->
        PlatformList(platformInterface = platformInterface, platformListViewModel, innerPadding = innerPadding)
    }
}