package interfaces

import entity.Game
import entity.GameEntity
import viewmodels.GameListViewModel

interface GameInterface {
    fun back()
    fun updateForward(gameListViewModel : GameListViewModel)
    fun updatePrevious(gameListViewModel : GameListViewModel)
    fun onClickGame(game: GameEntity)
    fun onShareGame(game: Game)
    fun onToggleFavorite(favorite: Boolean)
}