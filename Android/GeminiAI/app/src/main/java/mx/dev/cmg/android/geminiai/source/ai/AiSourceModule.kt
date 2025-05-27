package mx.dev.cmg.android.geminiai.source.ai

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AiSourceModule {

    @Binds
    abstract fun bindAiSource(impl: AISourceImpl): AISource
}