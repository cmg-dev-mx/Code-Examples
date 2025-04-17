package mx.dev.cmg.android.jobscheduler.repository.source

import mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler.MyJobService

interface MyJobScheduler {
    suspend fun scheduleJob(scheduleId: Int, dayInterval: Long, service: MyJobService): Boolean
    suspend fun stopJob(id: Int): Boolean
}
