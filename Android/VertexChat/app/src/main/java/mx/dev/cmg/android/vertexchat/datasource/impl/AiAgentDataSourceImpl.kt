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
        .generativeModel(modelStr)

    private val chat by lazy {
        val request = """
            "Act as a necromancer who can help me learn dutch (netherlands language).
            I'm a native spanish speaker and I want to learn  dutch (netherlands language).
            Talk to me in spanish and help me learn  dutch (netherlands language)."
        """.trimIndent()

        model.startChat(
            history = listOf(
                content(role = "user") { text(request) },
                content(role = "model") { text("Sure! I can help you with that.") }
            )
        )
    }

    override suspend fun queryPrompt(prompt: String): String {
        val response = chat.sendMessage(prompt)
        return response.text ?: "Error in querying!"
    }
}