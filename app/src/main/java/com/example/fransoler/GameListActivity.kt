package com.example.fransoler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import common.Constant
import entidades.Game
import entidades.GameEntity
import entidades.enums.BottomBarState
import interfaces.GameInterface
import ui.components.game.GameList
import ui.theme.AppTheme
import viewmodels.GameListViewModel

class GameListActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var currentPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformId = intent.getIntExtra(Constant.PLATFORM_ID,0)
        currentPage = intent.getIntExtra(Constant.PAGE,1)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameList(this@GameListActivity, platformId, currentPage)
                }
            }
        }
    }

    override fun onClickGame(game: GameEntity) {
        val intent = Intent(this,GameInfoActivity::class.java)
        intent.putExtra(Constant.DESTINATION, BottomBarState.HOME.ordinal)
        intent.putExtra(Constant.GAME_ID, game.id)
        intent.putExtra(Constant.PLATFORM_ID, platformId)
        intent.putExtra(Constant.PAGE, currentPage)
        startActivity(intent)
    }

    override fun onShareGame(game: Game) {
        //Nada
    }

    override fun onToggleFavorite(favorite: Boolean) {
        //Nada
    }

    override fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun updateForward(gameListViewModel: GameListViewModel) {
        if(gameListViewModel.next != null){
            currentPage++
            gameListViewModel.updatePage(currentPage)
        }
    }

    override fun updatePrevious(gameListViewModel: GameListViewModel) {
        if(currentPage > 1){
            currentPage--
            gameListViewModel.updatePage(currentPage)
        }
    }
    
}