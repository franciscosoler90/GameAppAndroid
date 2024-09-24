/*
 * Copyright (c) 2024. Francisco José Soler Conchello
 */

package common

object Constant {
    const val appName = "GameNexus"

    const val BASE_URL="https://api.rawg.io/api/"
    const val API_KEY = dotenv()["API_KEY"] ?: "default_key"

    const val urlGames = "https://rawg.io/games/"

    const val platformId="platformId"
    const val gameId="gameId"
    const val page="page"

    const val destination="destination"

}
