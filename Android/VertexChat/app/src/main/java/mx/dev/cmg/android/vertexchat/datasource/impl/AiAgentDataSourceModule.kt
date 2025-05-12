package mx.dev.cmg.android.vertexchat.datasource.impl

import androidx.annotation.BinderThread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class AiAgentDataSourceModule {

    @Binds
    abstract fun bindAiAgentDataSource(impl: AiAgentDataSourceImpl): AiAgentDataSource
}