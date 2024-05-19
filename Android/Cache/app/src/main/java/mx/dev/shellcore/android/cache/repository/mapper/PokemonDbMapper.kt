package mx.dev.shellcore.android.cache.repository.mapper

import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.db.model.PokemonDo

object PokemonDbMapper {

    fun mapToPokemonDb(pokemon: Pokemon) =
        PokemonDo(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl
        )

    fun mapToPokemon(pokemonDb: PokemonDo) =
        Pokemon(
            id = pokemonDb.id,
            name = pokemonDb.name,
            imageUrl = pokemonDb.imageUrl
        )
}