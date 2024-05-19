package mx.dev.shellcore.android.cache.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.cache.db.base.PokemonDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providePokemonDao(pokemonDb: PokemonDb) = pokemonDb.pokemonDao()

    @Singleton
    @Provides
    fun providePokemonDb(@ApplicationContext context: Context): PokemonDb =
        Room.databaseBuilder(
            context,
            PokemonDb::class.java,
            PokemonDb.DATABASE_NAME
        ).build()
}