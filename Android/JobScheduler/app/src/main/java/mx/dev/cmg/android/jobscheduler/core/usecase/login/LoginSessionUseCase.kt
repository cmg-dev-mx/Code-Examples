package mx.dev.cmg.android.jobscheduler.core.usecase.login

import kotlinx.coroutines.flow.Flow

interface LoginSessionUseCase {
    suspend fun isLoggedIn(): Flow<Boolean>
}
