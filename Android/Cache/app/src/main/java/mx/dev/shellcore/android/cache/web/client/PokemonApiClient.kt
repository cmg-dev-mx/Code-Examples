package mx.dev.shellcore.android.cache.web.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}