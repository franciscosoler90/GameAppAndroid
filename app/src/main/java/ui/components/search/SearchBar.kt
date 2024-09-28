package ui.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import interfaces.GameInterface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(gameInterface: GameInterface, title: String) {

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        inputField = {
            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text(text = "Buscar en $title") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )
        },
        expanded = active,
        onExpandedChange = { active = it },
        modifier = Modifier.fillMaxWidth(),
        shape = SearchBarDefaults.inputFieldShape,
        colors = SearchBarDefaults.colors(),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        windowInsets = SearchBarDefaults.windowInsets,
        content = {
            // Aquí puedes agregar contenido adicional, si es necesario.
        }
    )
}
