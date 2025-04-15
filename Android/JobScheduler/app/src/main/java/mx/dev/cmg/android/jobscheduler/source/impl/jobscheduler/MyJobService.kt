package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.os.HandlerThread
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification

class MyJobService: JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        val handlerThread = HandlerThread("JobThread")
        handlerThread.start()

        val handler = Handler(handlerThread.looper)
        handler.post {
            applicationContext.createNotification(
                title = "Job Service",
                content = "Job is running",
            )
            jobFinished(params, true)
        }

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}
