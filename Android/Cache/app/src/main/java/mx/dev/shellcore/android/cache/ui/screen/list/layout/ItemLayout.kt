package mx.dev.shellcore.android.cache.ui.screen.list.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.ui.theme.CacheTheme

@Preview
@Composable
private fun ItemLayoutPreview() {
    val bulbasaur = Pokemon(
        id = 1,
        name = "Bulbasaur",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    )

    CacheTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ItemLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                pokemon = bulbasaur
            )
        }
    }
}

@Composable
fun ItemLayout(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = pokemon.imageUrl,
                contentDescription = null
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "ID: ${pokemon.id}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}