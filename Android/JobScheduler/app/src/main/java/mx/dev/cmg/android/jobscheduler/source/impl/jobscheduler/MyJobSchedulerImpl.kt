package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.dev.cmg.android.jobscheduler.repository.source.MyJobScheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MyJobSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MyJobScheduler {

    private val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

    override suspend fun scheduleJob(scheduleId: Int): Boolean {
        val name = ComponentName(context, MyJobService::class.java)
        val result = jobScheduler.schedule(getJobInfo(scheduleId, name))
        return result != JobScheduler.RESULT_FAILURE
    }

    override suspend fun stopJob(scheduleId: Int): Boolean {
        jobScheduler.cancel(scheduleId)
        return true
    }

    private fun getJobInfo(
        id: Int,
        name: ComponentName
    ): JobInfo {
        val interval = TimeUnit.MINUTES.toMillis(1)
        val isPersistent = true

        val jobInfo = JobInfo.Builder(id, name)
            .setMinimumLatency(interval)
            .setPersisted(isPersistent)
            .build()

        return jobInfo
    }
}