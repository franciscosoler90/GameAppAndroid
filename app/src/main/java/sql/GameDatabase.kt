package sql

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import entity.GameEntity
import entity.GenreListConverter

@Database(entities = [GameEntity::class], version = 14, exportSchema = false)
@TypeConverters(GenreListConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDAO
}