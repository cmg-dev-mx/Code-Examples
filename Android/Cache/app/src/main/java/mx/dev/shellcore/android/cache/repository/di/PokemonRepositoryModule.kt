package mx.dev.shellcore.android.cache.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.cache.repository.base.PokemonRepository
import mx.dev.shellcore.android.cache.repository.impl.PokemonRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonRepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository
}