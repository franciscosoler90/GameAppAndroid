package common

import io.github.cdimascio.dotenv.dotenv

object ApiConfig {
    val API_KEY: String = dotenv()["API_KEY"] ?: "default_key"
}