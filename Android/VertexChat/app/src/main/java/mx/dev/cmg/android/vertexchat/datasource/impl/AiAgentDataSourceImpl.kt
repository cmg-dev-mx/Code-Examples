package mx.dev.cmg.android.vertexchat.datasource.impl

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource
import javax.inject.Inject

class AiAgentDataSourceImpl @Inject constructor() : AiAgentDataSource {

    private val modelStr = "gemini-2.0-flash" // TODO Mover a Remote Config
    private val model = Firebase
        .ai(backend = GenerativeBackend.vertexAI())
        .generativeModel(
            modelName = modelStr,
            systemInstruction = content {
                text("""
            You're a necromancer who can help to learn dutch (netherlands language).
            Recognize my language and respond in the same language.
            Talk to me in spanish and help me learn  dutch (netherlands language).
        """)
            }
        )

    private val chat = model.startChat()

    override suspend fun queryPrompt(prompt: String): String {
        val response = chat.sendMessage(prompt)
        return response.text ?: "Error!"
    }
}