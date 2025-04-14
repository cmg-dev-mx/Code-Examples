package mx.dev.cmg.android.jobscheduler.repository.impl.notification

import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource,
) : NotificationRepository {
    
    override suspend fun isWelcomeNotificationAlreadyShown() =
        dataStoreSource.welcomeNotificationShown()

    override suspend fun setWelcomeNotificationShown() {
        dataStoreSource.setWelcomeNotificationShown()
    }
}