package mx.dev.cmg.android.jobscheduler.repository.impl.notification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import mx.dev.cmg.android.jobscheduler.repository.source.MyJobScheduler
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource,
    private val scheduler: MyJobScheduler
) : NotificationRepository {
    
    override suspend fun isWelcomeNotificationAlreadyShown() =
        dataStoreSource.welcomeNotificationShown()

    override suspend fun setWelcomeNotificationShown() {
        dataStoreSource.setWelcomeNotificationShown()
    }

    override suspend fun setupOneDayNotification() = flow {
        val result = scheduler.scheduleJob(123)
        emit(result)
    }

    override suspend fun stopJob(): Flow<Boolean> {
        return flow {
            scheduler.stopJob(123)
            emit(true)
        }
    }

    override suspend fun setOneDayNotificationShown() {
        dataStoreSource.setOneDayNotificationShown()
    }

    override suspend fun isOneDayWithoutLoginNotificationAlreadyShown(): Flow<Boolean> {
        return dataStoreSource.oneDayNotificationShown()
    }
}