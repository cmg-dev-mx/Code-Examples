package mx.dev.shellcore.android.notas.core.uc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.android.notas.core.uc.base.GetNotesUseCase
import mx.dev.shellcore.android.notas.core.uc.impl.GetNotesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetNotesUseCaseModule {

    @Binds
    abstract fun bindGetNotesUseCase(impl: GetNotesUseCaseImpl): GetNotesUseCase
}