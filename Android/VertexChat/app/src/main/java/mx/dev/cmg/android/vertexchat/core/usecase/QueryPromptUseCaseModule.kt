package mx.dev.cmg.android.vertexchat.core.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class QueryPromptUseCaseModule {

    @Binds
    abstract fun bindQueryPromptUseCase(impl: QueryPromptUseCaseImpl): QueryPromptUseCase
}