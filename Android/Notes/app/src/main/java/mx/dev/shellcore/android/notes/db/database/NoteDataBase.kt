package mx.dev.shellcore.android.notes.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.dev.shellcore.android.notes.db.dao.NoteDao
import mx.dev.shellcore.android.notes.db.model.NoteDO

@Database(
    entities = [
        NoteDO::class
    ],
    version = 1
)
abstract class NoteDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note_database"
    }

    abstract fun noteDao(): NoteDao
}