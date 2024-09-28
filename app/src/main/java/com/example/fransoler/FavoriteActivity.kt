package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import common.Constant
import entidades.GameEntity
import entidades.Platform
import entidades.enums.BottomBarState
import interfaces.NavigationInterface
import sql.GameDatabase
import ui.components.favorite.FavoriteView
import ui.theme.AppTheme
import viewmodels.GameFavoriteViewModel

class FavoriteActivity : ComponentActivity(), NavigationInterface {

    private lateinit var gameFavoriteViewModel: GameFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameDatabase = Room.databaseBuilder(applicationContext, GameDatabase::class.java, "game-db").fallbackToDestructiveMigration().build()
        gameFavoriteViewModel = GameFavoriteViewModel(gameDatabase)

        val currentUser = FirebaseAuth.getInstance().currentUser

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FavoriteView(currentUser, navigationInterface = this@FavoriteActivity, gameFavoriteViewModel)
                }
            }
        }
    }

    override fun homeRoute() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun searchRoute() {
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }

    override fun favoriteRoute() {
        //Nada
    }

    override fun onClickPlatform(platform: Platform) {
        //Nada
    }

    override fun onClickGame(game: GameEntity) {
        val intent = Intent(this,GameInfoActivity::class.java)
        intent.putExtra(Constant.DESTINATION, BottomBarState.FAVORITE.ordinal)
        intent.putExtra(Constant.GAME_ID, game.id)
        startActivity(intent)
    }
}