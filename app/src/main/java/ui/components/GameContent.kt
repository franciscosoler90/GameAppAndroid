/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.Genre
import entity.Platforms
import viewmodels.GameInfoViewModel

@Composable
fun GameContent(game: GameInfoViewModel) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
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
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                if(game.released.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    DateComponent(
                        title = "Fecha de lanzamiento",
                        description = game.released)
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
                        Text(
                            text = "Metascore",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(8.dp))
                        MetacriticCard(metacritic = game.metacritic)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Acerca de",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = game.description,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
fun DateComponent(title: String, description: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall)
    }
}



@Composable
fun TagsPlatforms(list : List<Platforms>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(
            text = "Plataformas",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)

        Spacer(modifier = Modifier.padding(4.dp))

        TagsRow(list = list.map { it.platform.name })
    }
}

@Composable
fun TagsGenres(list : List<Genre>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(
            text = "Géneros",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)

        Spacer(modifier = Modifier.padding(4.dp))

        TagsRow(list = list.map { it.name })

    }
}

@Composable
fun TagsRow(list : List<String>) {

    // Utilizar LazyRow para organizar los Tags en filas
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list) { text ->
            TagElement(text = text)
        }
    }

}

@Composable
fun TagElement(text : String) {
    Surface(
        modifier = Modifier.padding(horizontal = 0.dp),
        shadowElevation = 2.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondaryContainer)
    }

    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun MetacriticCard(metacritic : Int){

    val ratingColor =
        if (metacritic <= 40) Color.Red else if (metacritic <= 70) Color.Yellow else Color.Green

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(1.dp, ratingColor),
        shape = RoundedCornerShape(6.dp),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            text = metacritic.toString(),
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = ratingColor
        )
    }

}
