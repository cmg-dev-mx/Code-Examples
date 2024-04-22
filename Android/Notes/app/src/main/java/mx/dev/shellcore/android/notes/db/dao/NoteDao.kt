package mx.dev.shellcore.android.notes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.dev.shellcore.android.notes.db.model.NoteDO

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun queryAll(): List<NoteDO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDO: NoteDO)
}