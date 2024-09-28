package viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.Platform

class PlatformListViewModel: ViewModel() {

    var platformList: List<Platform> by mutableStateOf(listOf())

    fun onInit() {
        loadData()
    }

    private fun loadData() {
        API.loadPlatforms({ result ->
            platformList = result.result.flatMap { platformParent -> platformParent.platforms }
                          },
            { println("Error - PlatformListViewModel") })
    }

}