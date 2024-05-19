package mx.dev.shellcore.android.cache.repository.impl

import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.android.cache.core.request.RequestState
import mx.dev.shellcore.android.cache.repository.base.PokemonRepository
import mx.dev.shellcore.android.cache.repository.mapper.PokemonMapper
import mx.dev.shellcore.android.cache.web.PokemonService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val service: PokemonService
) : PokemonRepository {

    override suspend fun getPokemonList() = try {
        flow {
            val response = service.getPokemonList()
            val pokemonList = PokemonMapper.invoke(response.results)
            emit(RequestState.Success(pokemonList))
        }
    } catch (e: Exception) {
        flow {
            emit(RequestState.Error(e))
        }
    }
}