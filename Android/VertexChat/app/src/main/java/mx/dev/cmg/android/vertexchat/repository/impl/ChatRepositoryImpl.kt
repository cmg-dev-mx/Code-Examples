package mx.dev.cmg.android.vertexchat.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.vertexchat.core.repository.ChatRepository
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val aiAgentDataSource: AiAgentDataSource
) : ChatRepository {

    override suspend fun queryPrompt(string: String): Flow<String> {
        return flow {
            emit(aiAgentDataSource.queryPrompt(string))
        }
    }
}