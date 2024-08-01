package mx.dev.shellcore.android.simplenotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.shellcore.android.simplenotifications.source.notification.SimpleNotificationManager
import mx.dev.shellcore.android.simplenotifications.ui.screen.main.layout.MainLayout
import mx.dev.shellcore.android.simplenotifications.ui.theme.SimpleNotificationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        SimpleNotificationManager.initNotificationManager(
            getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        )
        SimpleNotificationManager.createNotificationChannel("DemoChannel", "This is  a demo")

        setContent {
            SimpleNotificationsTheme {
                MainLayout()
            }
        }
    }
}