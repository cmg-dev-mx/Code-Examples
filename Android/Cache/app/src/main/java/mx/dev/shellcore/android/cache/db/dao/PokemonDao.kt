package mx.dev.shellcore.android.cache.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.dev.shellcore.android.cache.db.model.PokemonDo

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getPokemonList(): List<PokemonDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonList(pokemonList: List<PokemonDo>)
}