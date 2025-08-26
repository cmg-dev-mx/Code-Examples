package mx.dev.cmg.android.vertexchat.repository.datasource

interface AiAgentDataSource {
    suspend fun queryPrompt(prompt: String): String
    suspend fun startChat()
    suspend fun stopChat()
}
