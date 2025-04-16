package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import mx.dev.cmg.android.jobscheduler.source.impl.notfication.DataStoreSourceImpl
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification
import javax.inject.Inject

class MyJobService @Inject constructor(): JobService() {

    private val dataStoreSource: DataStoreSource by lazy {
        DataStoreSourceImpl(context = applicationContext)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        applicationContext.createNotification(
            title = "Job Service",
            content = "Job is running",
        )

        CoroutineScope(Dispatchers.IO).launch {
            dataStoreSource.setOneDayNotificationShown()
            Log.d("MOGC", "Job finished")
        }

        jobFinished(params, false)

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}
