package mx.dev.cmg.android.jobscheduler

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotificationChannel

@HiltAndroidApp
class JobSchedulerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
}