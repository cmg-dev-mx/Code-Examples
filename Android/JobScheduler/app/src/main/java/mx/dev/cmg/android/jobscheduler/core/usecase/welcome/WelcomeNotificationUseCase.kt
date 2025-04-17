package mx.dev.cmg.android.jobscheduler.core.usecase.welcome

import kotlinx.coroutines.flow.Flow

interface WelcomeNotificationUseCase {
    suspend fun validateWelcomeNotification(): Flow<Boolean>
}

