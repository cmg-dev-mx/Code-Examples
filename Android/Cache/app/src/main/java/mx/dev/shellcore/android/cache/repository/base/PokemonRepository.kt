package mx.dev.shellcore.android.cache.repository.base

import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState

interface PokemonRepository {
    suspend fun getPokemonList(): RequestState<List<Pokemon>>
}
