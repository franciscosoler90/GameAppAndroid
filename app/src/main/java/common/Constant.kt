package common
import io.github.cdimascio.dotenv.dotenv


object Constant {
    val API_KEY = dotenv()["API_KEY"] ?: "default_key"
    const val APP_NAME = "GameNexus"
    const val BASE_URL="https://api.rawg.io/api/"
    const val URL = "https://rawg.io/games/"
    const val PLATFORM_ID="platformId"
    const val GAME_ID="gameId"
    const val PAGE="page"
    const val DESTINATION="destination"
}