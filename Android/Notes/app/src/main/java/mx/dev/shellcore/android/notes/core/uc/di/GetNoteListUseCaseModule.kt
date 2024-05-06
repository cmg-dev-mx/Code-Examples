package mx.dev.shellcore.android.notes.core.uc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteListUseCase
import mx.dev.shellcore.android.notes.core.uc.impl.GetNoteListUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetNoteListUseCaseModule {

     @Binds
     abstract fun bindGetNoteListUseCase(useCase: GetNoteListUseCaseImpl): GetNoteListUseCase
}