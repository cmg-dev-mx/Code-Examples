package mx.dev.shellcore.android.cache.web.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.cache.web.PokemonService
import mx.dev.shellcore.android.cache.web.client.PokemonApiClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonApiModule {

    @Singleton
    @Provides
    fun providePokemonApi(): PokemonService =
        PokemonApiClient
            .retrofit
            .create(PokemonService::class.java)
}