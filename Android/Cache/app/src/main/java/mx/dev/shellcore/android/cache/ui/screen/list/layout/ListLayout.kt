package mx.dev.shellcore.android.cache.ui.screen.list.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.ui.theme.CacheTheme

@Composable
fun ListLayout() {
    ListLayoutContent()
}

@Preview
@Composable
private fun ListLayoutPreview() {

    val list = listOf(
        Pokemon(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        Pokemon(
            id = 2,
            name = "Ivysaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
        ),
        Pokemon(
            id = 3,
            name = "Venusaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
        ),
        Pokemon(
            id = 4,
            name = "Charmander",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
        ),
        Pokemon(
            id = 5,
            name = "Charmeleon",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"
        ),
        Pokemon(
            id = 6,
            name = "Charizard",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"
        ),
    )

    CacheTheme {
        ListLayoutContent(list)
    }
}

@Composable
private fun ListLayoutContent(list: List<Pokemon> = emptyList()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { parentPadding ->
        if (list.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No data available",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(parentPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                list.forEach {
                    item {
                        ItemLayout(
                            modifier = Modifier.fillMaxWidth(),
                            pokemon = it
                        )
                    }
                }
            }
        }
    }
}
