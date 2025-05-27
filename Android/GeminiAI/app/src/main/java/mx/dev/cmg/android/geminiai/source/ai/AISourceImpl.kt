package mx.dev.cmg.android.geminiai.source.ai

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import mx.dev.cmg.android.geminiai.source.config.remoteConfig
import javax.inject.Inject

class AISourceImpl @Inject constructor() : AISource {

    private val model = remoteConfig.getString("ai_model")

    val systemInstruction = """
        
        Eres un asistente llamada Chi. Ayudas a los usuarios a realizar la agenda de actividades del día,
        responder preguntas sobre la agenda, y proporcionar información útil relacionada con las actividades diarias.
        
        Todas las respuestas deben ser en español.
        
        Al final de cada respuesta, debes incluir un emoji relacionado con la actividad o pregunta.
        Si no tienes suficiente información para responder, indica que no puedes ayudar con esa pregunta.
        
    """.trimIndent()


    private val generativeModel = Firebase
        .ai(backend = GenerativeBackend.vertexAI())
        .generativeModel(model, systemInstruction = content {
            text(systemInstruction)
        })

    val chat = generativeModel.startChat()

    override suspend fun askQuestion(question: String): String {
        val response = chat.sendMessage(question)
        return response.text ?: "Lo siento, no puedo ayudar con esa pregunta."
    }
}