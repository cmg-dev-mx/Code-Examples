package mx.dev.shellcore.android.cache.repository.impl

import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.android.cache.core.request.RequestState
import mx.dev.shellcore.android.cache.db.dao.PokemonDao
import mx.dev.shellcore.android.cache.repository.base.PokemonRepository
import mx.dev.shellcore.android.cache.repository.mapper.PokemonApiMapper
import mx.dev.shellcore.android.cache.repository.mapper.PokemonDbMapper
import mx.dev.shellcore.android.cache.web.PokemonService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val service: PokemonService,
    private val dao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList() = try {
        flow {
            val dbResult = dao.getPokemonList()
            if (dbResult.isNotEmpty()) {
                emit(RequestState.Success(dbResult.map { PokemonDbMapper.mapToPokemon(it) }))
            } else {
                val response = service.getPokemonList()
                val pokemonList = PokemonApiMapper.invoke(response.results)
                dao.savePokemonList(pokemonList.map { PokemonDbMapper.mapToPokemonDb(it) })
                emit(RequestState.Success(pokemonList))
            }
        }
    } catch (e: Exception) {
        flow {
            emit(RequestState.Error(e))
        }
    }
}