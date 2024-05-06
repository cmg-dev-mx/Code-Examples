package mx.dev.shellcore.android.notes.core.uc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.impl.GetNoteUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetNoteUseCaseModule {

    @Binds
    abstract fun bindGetNoteUseCase(useCase: GetNoteUseCaseImpl): GetNoteUseCase
}