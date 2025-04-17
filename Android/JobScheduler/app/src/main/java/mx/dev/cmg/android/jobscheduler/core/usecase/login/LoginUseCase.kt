package mx.dev.cmg.android.jobscheduler.core.usecase.login

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(): Flow<Boolean>
}
