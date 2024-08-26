package mx.dev.shellcore.android.cache.repository.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<RequestState<List<Pokemon>>>
}
