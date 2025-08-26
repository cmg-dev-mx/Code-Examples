package mx.dev.cmg.android.vertexchat.core.repository

import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun queryPrompt(prompt: String): Flow<String>
    suspend fun startChat()
    suspend fun stopChat()
}
