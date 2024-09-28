package entity

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null
)

class GenreListConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return genres?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toGenreList(genresString: String?): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return genresString?.let { Gson().fromJson(it, listType) }
    }
}