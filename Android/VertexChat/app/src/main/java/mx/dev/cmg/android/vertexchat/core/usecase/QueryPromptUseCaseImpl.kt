package mx.dev.cmg.android.vertexchat.core.usecase

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.vertexchat.core.repository.ChatRepository
import javax.inject.Inject

class QueryPromptUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): QueryPromptUseCase {

    override suspend fun invoke(prompt: String): Flow<String> {
        return repository.queryPrompt(prompt)
    }
}