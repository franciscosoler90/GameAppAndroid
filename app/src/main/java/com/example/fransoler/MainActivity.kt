package com.example.fransoler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import common.Constant
import entity.GameEntity
import entity.Platform
import interfaces.NavigationInterface
import interfaces.PlatformInterface
import ui.components.platforms.PlatformView
import ui.theme.AppTheme
import viewmodels.PlatformListViewModel

class MainActivity : AppCompatActivity(), PlatformInterface, NavigationInterface {

    private val platformListViewModel = PlatformListViewModel()
    private val platformInterface = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformListViewModel.onInit()

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlatformView(platformInterface, platformInterface, platformListViewModel)
                }
            }
        }
    }

    override fun onClickPlatform(platform: Platform) {
        val intent = Intent(this,GameListActivity::class.java)
        intent.putExtra(Constant.PLATFORM_ID, platform.id)
        startActivity(intent)
    }

    override fun searchRoute() {
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }

    override fun favoriteRoute() {
        val intent = Intent(this,FavoriteActivity::class.java)
        startActivity(intent)
    }

    override fun onClickGame(game: GameEntity) {
        //Nada
    }

    override fun homeRoute() {
        //Nada
    }

}