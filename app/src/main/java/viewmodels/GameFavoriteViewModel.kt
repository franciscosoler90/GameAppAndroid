package viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entidades.GameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sql.GameDAO
import sql.GameDatabase

class GameFavoriteViewModel(gameDatabase: GameDatabase) : ViewModel() {

    private val _favoriteList = mutableStateOf<List<GameEntity>>(emptyList())
    val favoriteList: State<List<GameEntity>> = _favoriteList

    private val gameFavoriteDAO: GameDAO = gameDatabase.gameDao()

    fun onInit(userId: String) {
        viewModelScope.launch {
            loadFavoriteGames(userId)
        }
    }

    private fun loadFavoriteGames(userId: String) {
        viewModelScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameFavoriteDAO.getAll(userId)
            }

            _favoriteList.value = games
        }
    }
}
