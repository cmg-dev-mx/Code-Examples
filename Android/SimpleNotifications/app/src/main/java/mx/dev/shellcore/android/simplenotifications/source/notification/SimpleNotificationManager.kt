package mx.dev.shellcore.android.simplenotifications.source.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import mx.dev.shellcore.android.simplenotifications.MainActivity


object SimpleNotificationManager {

    private const val NAMESPACE = "mx.dev.shellcore.android.simplenotifications"
    private const val CHANNEL_ID = "$NAMESPACE.channelId"
    const val KEY_REPLY = "key_reply"

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
        uri: String? = null,
        navigateWithButton: Boolean = false,
        requireReply: Boolean = false
    ) {

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon)
            .setPriority(priority)

        uri?.let {

            val intent = Intent(
                Intent.ACTION_VIEW,
                uri.toUri(),
                context,
                MainActivity::class.java
            )

            val pendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_MUTABLE)
            }

            val navigateWithAction = if (navigateWithButton) {
                NotificationCompat.Action.Builder(0, "Go to screen", pendingIntent).build()
            } else null

            val replyAction = if (requireReply) {
                val remoteInput = RemoteInput.Builder(KEY_REPLY).run {
                    setLabel("Write your name")
                    build()
                }

                NotificationCompat.Action.Builder(
                    0,
                    "REPLY",
                    pendingIntent
                ).addRemoteInput(remoteInput)
                    .build()

            } else null

            when {
                navigateWithButton -> notificationBuilder.addAction(navigateWithAction)
                requireReply -> notificationBuilder.addAction(replyAction)
                else -> notificationBuilder.setContentIntent(pendingIntent)
            }
        }

        val notification = notificationBuilder.build()

        notificationManager?.notify(1, notification)
    }
}
