package mx.dev.shellcore.android.simplenotifications.source.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat


object SimpleNotificationManager {

    private val namespace = "mx.dev.shellcore.android.simplenotifications"
    val CHANNEL_ID = "$namespace.channelId"

    private var notificationManager: NotificationManager? = null

    fun initNotificationManager(notificationManager: NotificationManager) {
        this.notificationManager = notificationManager
    }

    fun createNotificationChannel(name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            this.description = description
        }

        notificationManager?.createNotificationChannel(channel)
    }

    fun createNotification(
        context: Context,
        title: String,
        message: String,
        icon: Int = android.R.drawable.ic_dialog_info,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon)
            .setPriority(priority)
            .build()

        notificationManager?.notify(1, notification)
    }
}
