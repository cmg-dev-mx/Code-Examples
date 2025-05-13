package mx.dev.cmg.android.vertexchat.repository.datasource

interface AiAgentDataSource {
    suspend fun queryPrompt(string: String): String
}
