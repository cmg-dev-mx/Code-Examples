package mx.dev.cmg.android.jobscheduler.repository.source

interface MyJobScheduler {
    suspend fun scheduleJob(scheduleId: Int): Boolean
    suspend fun stopJob(i: Int): Boolean
}
