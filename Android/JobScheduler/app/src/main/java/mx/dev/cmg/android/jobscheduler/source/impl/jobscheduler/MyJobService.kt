package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import mx.dev.cmg.android.jobscheduler.source.impl.notfication.DataStoreSourceImpl

abstract class MyJobService: JobService() {

    abstract fun onJobStarted()

    private val dataStoreSource: DataStoreSource by lazy {
        DataStoreSourceImpl(context = applicationContext)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        onJobStarted()

        CoroutineScope(Dispatchers.IO).launch {
            dataStoreSource.setOneDayNotificationShown()
        }
        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}
