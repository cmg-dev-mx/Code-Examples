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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState
import mx.dev.shellcore.android.cache.ui.screen.list.vm.ListViewModel
import mx.dev.shellcore.android.cache.ui.theme.CacheTheme

@Composable
fun ListLayout() {

    val vm: ListViewModel = hiltViewModel()
    val listState = vm.listState.collectAsState().value

    LaunchedEffect(key1 = null) {
        vm.getPokemonList()
    }

    ListLayoutContent(listState)
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

    val listState = RequestState.Success(list)

    CacheTheme {
        ListLayoutContent(listState)
    }
}

@Composable
private fun ListLayoutContent(listState: RequestState<List<Pokemon>>) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { parentPadding ->
        when (listState) {
            RequestState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            is RequestState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: ${listState.exception.message}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            is RequestState.Success -> {
                if (listState.getSuccessData().isNullOrEmpty()) {
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
                        listState.getSuccessData()?.forEach {
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

            else -> {
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
            }
        }
    }
}
