package mx.dev.cmg.android.jetai

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.AuthRepositoryImpl
import mx.dev.cmg.android.jetai.data.repository.GoogleAuthClient

object Graph {

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl()
    }

    lateinit var googleAuthClient: GoogleAuthClient

    fun provide(context: Context) {
        googleAuthClient = GoogleAuthClient(
            oneTapClient = Identity.getSignInClient(context)
        )
    }
}