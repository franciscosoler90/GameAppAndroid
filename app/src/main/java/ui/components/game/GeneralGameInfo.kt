package ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fransoler.R
import entity.enums.ConverterDate
import entity.Game
import utils.convertDateTo

@Composable
fun GeneralGameInfo(
    game: Game,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            if (game.metacritic > 0) {
                InfoCard(title = stringResource(id = R.string.metascore_title), value = game.metacritic.toString())
            }

            if (game.rating > 0) {
                Column {
                    Text(
                        text = stringResource(id = R.string.rating_title),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RatingBar(rating = game.rating.toString(), modifier = Modifier.height(16.dp))
                }
            }
        }

        game.released?.takeIf { it.isNotBlank() }?.let {
            it.convertDateTo(ConverterDate.FULL_DATE)?.let { date ->
                Section(title = stringResource(id = R.string.release_date_title), content = date)
            }
        }

        val genreNames = game.genres.mapNotNull { it.name }.sorted()
        val platformNames = game.platforms.map { it.platform.name }.sorted()

        if (genreNames.isNotEmpty()) {
            TagSection(title = stringResource(id = R.string.genres_title), tags = genreNames)
        }

        if (platformNames.isNotEmpty()) {
            TagSection(title = stringResource(id = R.string.platforms_title), tags = platformNames)
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun Section(title: String, content: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun TagSection(title: String, tags: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        TagGroup(tag = tags, modifier, false)
    }
}

