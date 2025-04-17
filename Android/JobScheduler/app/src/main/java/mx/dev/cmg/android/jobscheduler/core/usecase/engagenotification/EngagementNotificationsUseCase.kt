package mx.dev.cmg.android.jobscheduler.core.usecase.engagenotification

interface EngagementNotificationsUseCase {
    suspend fun startEngagementNotifications(): Boolean
    suspend fun stopEngagementNotifications(): Boolean
}
