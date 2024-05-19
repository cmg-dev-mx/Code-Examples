package mx.dev.shellcore.android.cache.web

import mx.dev.shellcore.android.cache.web.model.PokemonListResponse
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList(): PokemonListResponse
}