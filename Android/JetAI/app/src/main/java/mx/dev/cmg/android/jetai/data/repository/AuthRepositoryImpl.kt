package mx.dev.cmg.android.jetai.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import mx.dev.cmg.android.jetai.utils.Response

class AuthRepositoryImpl : AuthRepository {

    override val currentUser: MutableStateFlow<FirebaseUser?> =
        MutableStateFlow(Firebase.auth.currentUser)

    override fun hasVerifiedUser(): Boolean {
        return Firebase.auth.currentUser?.isEmailVerified ?: false
    }

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun getUserId(): String {
        return Firebase.auth.currentUser?.uid.orEmpty()
    }

    override fun sendVerificationEmail(
        onSuccess: () -> Unit,
        onError: (error: Throwable?) -> Unit
    ) {
        Firebase
            .auth
            .currentUser
            ?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.cause)
                }
            }
    }

    override suspend fun login(email: String, password: String): Flow<Response<AuthResult?>> =
        callbackFlow {
            try {
                trySend(Response.Loading())
                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        currentUser.value = it.result.user
                        if (it.isSuccessful) {
                            trySend(Response.Success(it.result))
                        } else {
                            trySend(Response.Error(it.exception))
                        }
                    }
            } catch (e: Exception) {
                trySend(Response.Error(e))
            }

            awaitClose {}
        }

    override suspend fun createUser(email: String, password: String): Flow<Response<AuthResult?>> =
        callbackFlow {
            try {
                trySend(Response.Loading())
                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            currentUser.value = it.result.user
                            trySend(Response.Success(it.result))
                        } else {
                            trySend(Response.Error(it.exception))
                        }
                    }
            } catch (e: Exception) {
                trySend(Response.Error(e))
            }

            awaitClose {}
        }

    override suspend fun signInWithCredentials(credentials: AuthCredential): Flow<Response<AuthResult?>> =
        callbackFlow {
            try {
                trySend(Response.Loading())
                Firebase.auth.signInWithCredential(credentials)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            trySend(Response.Success(it.result))
                        } else {
                            trySend(Response.Error(it.exception))
                        }
                    }
            } catch (e: Exception) {
                trySend(Response.Error(e))
            }
            awaitClose {}
        }

    override suspend fun sendPasswordResetLink(email: String): Flow<Response<Boolean>> =
        callbackFlow {
            try {
                trySend(Response.Loading())
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            trySend(Response.Success(true))
                        } else {
                            trySend(Response.Error(it.exception))
                        }
                    }
            } catch (e: Exception) {
                trySend(Response.Error(e))
            }
            awaitClose {}
        }

    override fun signOut() {
        Firebase.auth.signOut()
        currentUser.value = null
    }
}