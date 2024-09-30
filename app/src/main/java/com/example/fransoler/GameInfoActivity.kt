package com.example.fransoler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import common.Constant
import utils.ContextUtilities
import entity.Game
import entity.GameEntity
import interfaces.GameInterface
import sql.GameDatabase
import ui.components.game.GameInfo
import ui.theme.AppTheme
import viewmodels.GameInfoViewModel
import viewmodels.GameListViewModel

class GameInfoActivity : AppCompatActivity(), GameInterface {

    private var platformId: Int = 0
    private var page: Int = 1
    private var destination: Int = 1

    private lateinit var gameInfoViewModel: GameInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameDatabase = try {
            DatabaseProvider.getDatabase(this)
        } catch (e: Exception) {
            println(R.string.errorDatabase)
            finish()
            return
        }

        gameInfoViewModel = GameInfoViewModel(gameDatabase)

        platformId = intent.getIntExtra(Constant.PLATFORM_ID, 0)
        page = intent.getIntExtra(Constant.PAGE, 1)
        destination = intent.getIntExtra(Constant.DESTINATION, 1)

        val gameId = intent.getLongExtra(Constant.GAME_ID, -1)
        if (gameId == -1L) {
            showErrorAndExit()
            return
        }

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameInfo(userId, gameId, gameInfoViewModel, this@GameInfoActivity)
                }
            }
        }
    }

    private fun showErrorAndExit() {
        ContextUtilities.showToast(this, R.string.favorites, 1)
        finish()
    }

    override fun back() {
        val intent = when (destination) {
            0 -> Intent(baseContext, GameListActivity::class.java).apply {
                putExtra(Constant.PLATFORM_ID, platformId)
                putExtra(Constant.PAGE, page)
            }
            1 -> Intent(baseContext, SearchActivity::class.java)
            2 -> Intent(baseContext, FavoriteActivity::class.java)
            else -> return
        }
        startActivity(intent)
    }

    override fun updateForward(gameListViewModel: GameListViewModel) {
        // Nada
    }

    override fun updatePrevious(gameListViewModel: GameListViewModel) {
        // Nada
    }

    override fun onClickGame(game: GameEntity) {
        // Nada
    }

    override fun onShareGame(game: Game) {
        ContextUtilities.shareGame(this, game)
    }

    override fun onToggleFavorite(favorite: Boolean) {
        if(favorite){
            ContextUtilities.showToast(this, R.string.addFavorite, 1)
        }else{
            ContextUtilities.showToast(this, R.string.deleteFavorite, 1)
        }
    }
}

object DatabaseProvider {
    private var instance: GameDatabase? = null

    fun getDatabase(context: Context): GameDatabase {
        return instance ?: synchronized(this) {
            val newInstance = Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                "game-db"
            ).fallbackToDestructiveMigration().build()
            instance = newInstance
            newInstance
        }
    }
}
