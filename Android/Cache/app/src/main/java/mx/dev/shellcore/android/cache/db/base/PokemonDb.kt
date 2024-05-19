package mx.dev.shellcore.android.cache.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.dev.shellcore.android.cache.db.dao.PokemonDao
import mx.dev.shellcore.android.cache.db.model.PokemonDo

@Database(
    entities = [
        PokemonDo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDb : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "pokemon.db"
    }

    abstract fun pokemonDao(): PokemonDao
}