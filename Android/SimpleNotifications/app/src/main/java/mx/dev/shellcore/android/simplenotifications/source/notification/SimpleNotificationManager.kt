package mx.dev.shellcore.android.simplenotifications.source.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import mx.dev.shellcore.android.simplenotifications.MainActivity


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
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        uri: String,
        actionString: String? = null

    ) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(uri),
            context,
            MainActivity::class.java
        )


        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }

        val action = if (actionString != null) {
            NotificationCompat.Action.Builder(0, actionString, pendingIntent).build()
        } else null

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon)
            .setPriority(priority)

        if (action != null) {
            notificationBuilder.addAction(action)
        } else {
            notificationBuilder.setContentIntent(pendingIntent)
        }

        val notification = notificationBuilder.build()

        notificationManager?.notify(1, notification)
    }
}
