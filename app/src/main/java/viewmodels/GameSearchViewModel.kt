package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.GameEntity

class GameSearchViewModel : ViewModel() {

    var listGames by mutableStateOf<List<GameEntity>>(emptyList())
    var onError: () -> Unit = {}

    fun searchGames(query : String, searchPrecise : Boolean, searchExact : Boolean) {
        API.searchGames(
            query,
            searchPrecise = searchPrecise,
            searchExact = searchExact,
            success = { game ->
            listGames = game.result
        }) {
            onError()
        }
    }
}