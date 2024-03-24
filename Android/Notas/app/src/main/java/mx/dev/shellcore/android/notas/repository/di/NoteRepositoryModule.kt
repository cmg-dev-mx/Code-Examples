package mx.dev.shellcore.android.notas.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.android.notas.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notas.repository.impl.NoteRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {

    @Binds
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository

}