/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import entidades.Game

@Dao
interface GameDAO {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg games: Game)

    @Delete
    fun delete(game: Game)

    @Query("SELECT COUNT(*) FROM game WHERE id = :gameId")
    fun isGameFavorite(gameId: Long): Int

}