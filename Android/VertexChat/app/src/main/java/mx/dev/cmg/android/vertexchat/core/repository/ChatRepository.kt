package mx.dev.cmg.android.vertexchat.core.repository

import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun queryPrompt(string: String): Flow<String>
}
