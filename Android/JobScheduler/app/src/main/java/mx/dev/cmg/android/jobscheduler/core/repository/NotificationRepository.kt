package mx.dev.cmg.android.jobscheduler.core.repository

import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun isWelcomeNotificationAlreadyShown(): Flow<Boolean>
    suspend fun setWelcomeNotificationShown()
}
