package mx.dev.shellcore.android.cache.repository.mapper

import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.web.model.Result

object PokemonApiMapper : Function1<List<Result>, List<Pokemon>> {

    override fun invoke(p1: List<Result>) =
        p1.map {
            mapToPokemon(it)
        }

    private fun mapToPokemon(result: Result) =
        Pokemon(
            id = result.url.obtainPokemonNumber(),
            name = result.name,
            imageUrl = result.url.obtainPokemonNumber().obtainImageUrl()
        )

    private fun String.obtainPokemonNumber() =
        this.substringAfter("pokemon/")
            .substringBefore("/")
            .toInt()

    private fun Int.obtainImageUrl() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$this.png"
}