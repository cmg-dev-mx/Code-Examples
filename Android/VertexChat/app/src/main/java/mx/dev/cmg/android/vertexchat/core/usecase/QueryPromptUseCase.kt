package mx.dev.cmg.android.vertexchat.core.usecase

import kotlinx.coroutines.flow.Flow

interface QueryPromptUseCase {
    suspend fun invoke(prompt: String): Flow<String>
}
