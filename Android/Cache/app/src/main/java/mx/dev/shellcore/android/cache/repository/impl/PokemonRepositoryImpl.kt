package mx.dev.shellcore.android.cache.repository.impl

import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState
import mx.dev.shellcore.android.cache.repository.base.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor() : PokemonRepository {

    override suspend fun getPokemonList(): RequestState<List<Pokemon>> {
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
            return RequestState.Success(pokemonList)
        } catch (e: Exception) {
            return RequestState.Error(e)
        }
    }
}