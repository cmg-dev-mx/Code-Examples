package mx.dev.cmg.android.jobscheduler.repository.source

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
    suspend fun welcomeNotificationShown(): Flow<Boolean>
    suspend fun setWelcomeNotificationShown()
    suspend fun setLogin(logged: Boolean): Flow<Boolean>
    suspend fun isLoggedIn(): Flow<Boolean>
}
