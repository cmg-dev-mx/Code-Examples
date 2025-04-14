package mx.dev.cmg.android.jobscheduler.core.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(): Flow<Boolean>
    suspend fun isLoggedIn(): Flow<Boolean>
}
