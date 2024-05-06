package mx.dev.shellcore.android.notes.db.source.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.notes.db.source.NoteDataSourceImpl
import mx.dev.shellcore.android.notes.repostitory.impl.NoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteDataSourceModule {

    @Binds
    abstract fun bindNoteDataSource(dataSource: NoteDataSourceImpl): NoteDataSource
}