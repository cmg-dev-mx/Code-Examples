package mx.dev.cmg.android.jobscheduler.core.usecase.engagenotification

import android.util.Log
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import javax.inject.Inject

class EngagementNotificationsUseCaseImpl @Inject constructor(
    private val repository: NotificationRepository
) : EngagementNotificationsUseCase {

    override suspend fun startEngagementNotifications(): Boolean {
        Log.d("MOGC", "startEngagementNotifications")
        repository.setupEngagementNotification(1030, 1)
        repository.setupEngagementNotification(1060, 2)
        return true
    }

    override suspend fun stopEngagementNotifications(): Boolean {
        repository.stopJob(1030)
        repository.stopJob(1060)
        return true
    }
}