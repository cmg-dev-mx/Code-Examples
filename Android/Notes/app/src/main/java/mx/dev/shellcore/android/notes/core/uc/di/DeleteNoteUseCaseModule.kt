package mx.dev.shellcore.android.notes.core.uc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.notes.core.uc.base.DeleteNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.impl.DeleteNoteUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DeleteNoteUseCaseModule {

    @Binds
    abstract fun bindDeleteNoteUseCase(deleteNoteUseCaseImpl: DeleteNoteUseCaseImpl): DeleteNoteUseCase
}