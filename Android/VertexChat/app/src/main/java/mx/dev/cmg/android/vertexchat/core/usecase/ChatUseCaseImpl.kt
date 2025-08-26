package mx.dev.cmg.android.vertexchat.core.usecase

import mx.dev.cmg.android.vertexchat.core.repository.ChatRepository
import javax.inject.Inject

class ChatUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : ChatUseCase {

    override suspend fun startChat() {
        chatRepository.startChat()
    }

    override suspend fun stopChat() {
        chatRepository.stopChat()
    }
}