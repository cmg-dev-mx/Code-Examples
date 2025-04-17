package mx.dev.cmg.android.jobscheduler.repository.impl.notification

import android.util.Log
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import mx.dev.cmg.android.jobscheduler.repository.source.MyJobScheduler
import mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler.Engagement30Service
import mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler.Engagement60Service
import mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler.OneDayNoLoginJobService
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
        val result = scheduler.scheduleJob(1001, 1, OneDayNoLoginJobService())
        emit(result)
    }

    override suspend fun stopJob(id: Int) = flow {
        scheduler.stopJob(id)
        emit(true)
    }

    override suspend fun setOneDayNotificationShown() {
        dataStoreSource.setOneDayNotificationShown()
    }

    override suspend fun isOneDayWithoutLoginNotificationAlreadyShown() =
        dataStoreSource.oneDayNotificationShown()

    override suspend fun setupEngagementNotification(id: Int, days: Long): Boolean {
        val result = when(id) {
            1030 -> {
                scheduler.scheduleJob(id, days, Engagement30Service())
            }
            1060 -> {
                scheduler.scheduleJob(id, days, Engagement60Service())
            }

            else -> false
        }

        Log.d("MOGC", "setupEngagementNotification $days days: $result")
        return true
    }
}