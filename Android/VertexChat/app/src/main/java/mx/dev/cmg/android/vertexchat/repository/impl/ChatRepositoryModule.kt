package mx.dev.cmg.android.vertexchat.repository.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.cmg.android.vertexchat.core.repository.ChatRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatRepositoryModule {

    @Binds
    abstract fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository
}