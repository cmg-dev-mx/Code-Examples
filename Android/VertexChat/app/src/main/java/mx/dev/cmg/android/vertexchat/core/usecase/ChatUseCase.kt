package mx.dev.cmg.android.vertexchat.core.usecase

interface ChatUseCase {
    suspend fun startChat()
    suspend fun stopChat()
}
