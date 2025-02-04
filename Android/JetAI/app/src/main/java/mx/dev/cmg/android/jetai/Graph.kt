package mx.dev.cmg.android.jetai

import android.content.Context
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.google.android.gms.auth.api.identity.Identity
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.AuthRepositoryImpl
import mx.dev.cmg.android.jetai.data.repository.GoogleAuthClient

object Graph {

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl()
    }

    lateinit var googleAuthClient: GoogleAuthClient

    private val config = generationConfig {
        temperature = 0.7f
    }

    fun generativeModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = BuildConfig.GEMINI_API_KEY,
        generationConfig = config
    )

    fun provide(context: Context) {
        googleAuthClient = GoogleAuthClient(
            oneTapClient = Identity.getSignInClient(context)
        )
    }
}