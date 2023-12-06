/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.textWhiteColor
import viewmodels.GameInfoViewModel

@Composable
fun GameContent(game: GameInfoViewModel) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    color = textWhiteColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                if(game.released.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoComponent(title = "Release date", description = game.released)
                }

                if(game.genres.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    TagsGenres(list = game.genres)
                }

                if(game.platforms.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    TagsPlatforms(list = game.platforms)
                }

                if(game.metacritic > 0) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Metascore", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        MetacriticCard(metacritic = game.metacritic)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "About",
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = game.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
