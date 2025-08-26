package mx.dev.cmg.android.vertexchat.datasource.impl

import android.Manifest
import androidx.annotation.RequiresPermission
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.LiveSession
import com.google.firebase.ai.type.PublicPreviewAPI
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.SpeechConfig
import com.google.firebase.ai.type.Voice
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.liveGenerationConfig
import mx.dev.cmg.android.vertexchat.repository.datasource.AiAgentDataSource
import javax.inject.Inject

@OptIn(PublicPreviewAPI::class)
class AiAgentDataSourceImpl @Inject constructor() : AiAgentDataSource {

    private val modelStr = "gemini-2.0-flash"
    private val chatModelStr = "gemini-live-2.5-flash-preview"
//    private val defaultVoice = "Despina"
    private val defaultVoice = "Leda"

    private val systemInstruction = """
        You're a necromancer who can help to learn dutch (netherlands language).
            Recognize my language and respond me in the same language.
            I'm more proficient to speak in latin american spanish.
    """.trimIndent()

    private val model = Firebase
        .ai(backend = GenerativeBackend.vertexAI())
        .generativeModel(
            modelName = modelStr,
            systemInstruction = content { text(systemInstruction) }
        )

    private val conversationModel = Firebase
        .ai(backend = GenerativeBackend.googleAI())
        .liveModel(
            modelName = chatModelStr,
            generationConfig = liveGenerationConfig {
                responseModality = ResponseModality.AUDIO
                speechConfig = SpeechConfig(voice = Voice(defaultVoice))
            },
            systemInstruction = content { text(systemInstruction) }
        )

    private val chat = model.startChat()

    private var conversation: LiveSession? = null


    override suspend fun queryPrompt(prompt: String): String {
        return try {
            val response = chat.sendMessage(prompt)
            response.text ?: "Error!"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    override suspend fun startChat() {
        conversation = conversationModel.connect()
        conversation?.startAudioConversation()
    }

    override suspend fun stopChat() {
        conversation?.stopAudioConversation()
    }
}