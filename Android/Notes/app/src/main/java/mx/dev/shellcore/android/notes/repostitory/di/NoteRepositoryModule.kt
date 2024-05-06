package mx.dev.shellcore.android.notes.repostitory.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.repostitory.impl.NoteRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {

    @Binds
    abstract fun bindNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}