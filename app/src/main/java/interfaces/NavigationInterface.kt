package interfaces

import entity.GameEntity
import entity.Platform

interface NavigationInterface {
    fun homeRoute()
    fun searchRoute()
    fun favoriteRoute()

    fun onClickPlatform(platform: Platform)
    fun onClickGame(game: GameEntity)
}