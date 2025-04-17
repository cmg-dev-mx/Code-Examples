package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification

class OneDayNoLoginJobService: MyJobService() {

    override fun onJobStarted() {
        applicationContext.createNotification(
            title = "Job Service",
            content = "Job is running",
        )
    }
}
