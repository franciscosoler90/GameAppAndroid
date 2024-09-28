package entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "favorite_games")
data class GameEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "background_image") val backgroundImage: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "genres") val genres: List<Genre>,
    @ColumnInfo(name = "userId") val userId: String
)

@JsonClass(generateAdapter = true)
data class Game(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "background_image") val backgroundImage: String?,
    @Json(name = "description") val description: String,
    @Json(name = "description_raw") val descriptionRaw: String,
    @Json(name = "released") val released: String?,
    @Json(name = "metacritic") val metacritic: Int,
    @Json(name = "rating") val rating: Float,
    @Json(name = "platforms") val platforms: List<Platforms>,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "developers") val developers: List<Developer>,
    @Json(name = "publishers") val publishers: List<Publisher>,
)

