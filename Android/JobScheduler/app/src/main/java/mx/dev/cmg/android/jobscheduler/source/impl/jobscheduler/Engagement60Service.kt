package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification

class Engagement60Service: MyJobService() {

    override fun onJobStarted() {
        applicationContext.createNotification(
            title = "Engagement",
            content = "Engagement 60 days",
        )
    }
}
