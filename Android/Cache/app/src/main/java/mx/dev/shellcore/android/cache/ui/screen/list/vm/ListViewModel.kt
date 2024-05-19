package mx.dev.shellcore.android.cache.ui.screen.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    private val _list = MutableStateFlow<RequestState<List<Pokemon>>>(RequestState.Idle)
    val listState = _list.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            _list.value = RequestState.Loading
            try {
                val pokemonList = listOf(
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
                _list.value = RequestState.Success(pokemonList)
            } catch (e: Exception) {
                _list.value = RequestState.Error(e)
            }
        }
    }
}