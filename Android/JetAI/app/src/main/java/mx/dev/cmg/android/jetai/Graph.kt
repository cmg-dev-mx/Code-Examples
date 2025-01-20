package mx.dev.cmg.android.jetai

import android.content.Context
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.AuthRepositoryImpl

object Graph {

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl()
    }

    fun provide(context: Context) {

    }
}