package mx.dev.cmg.android.vertexchat.datasource.impl

import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource
import javax.inject.Inject

class AiAgentDataSourceImpl @Inject constructor() : AiAgentDataSource {

    private val model = Firebase.vertexAI.generativeModel("gemini-2.0-flash") // TODO Mover a Remote Config

    override suspend fun queryPrompt(string: String): String {
        val response = model.generateContent(string)
        return response.text ?: "Error in querying!"
    }
}