package mx.dev.cmg.android.jobscheduler.source.impl.jobscheduler

import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification

class Engagement30Service: MyJobService() {

    override fun onJobStarted() {
        applicationContext.createNotification(
            title = "Engagement",
            content = "Engagement 30 days",
        )
    }
}
