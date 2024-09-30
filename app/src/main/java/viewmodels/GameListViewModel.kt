package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.GameEntity

class GameListViewModel(private val platformId: Int, page: Int) : ViewModel() {

    var gameList: List<GameEntity> by mutableStateOf(listOf())
    var next: String? by mutableStateOf("")

    init {
        loadData(page)
    }

    fun updatePage(pageUpdate: Int) {
        loadData(pageUpdate)
    }

    private fun loadData(page: Int) {
        API.loadGames(platformId,page,{ result ->
            this.gameList = result.result
            this.next = result.next
        }, {
            println("Error - GameListViewModel - loadData")
        })
    }
}
