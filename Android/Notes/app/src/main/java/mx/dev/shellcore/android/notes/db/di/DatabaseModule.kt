package mx.dev.shellcore.android.notes.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.notes.db.database.NoteDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNoteDao(db: NoteDataBase) = db.noteDao()

    @Provides
    @Singleton
    fun providesNoteDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()
}