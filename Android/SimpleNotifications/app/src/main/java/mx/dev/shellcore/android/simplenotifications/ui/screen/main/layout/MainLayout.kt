package mx.dev.shellcore.android.simplenotifications.ui.screen.main.layout

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import mx.dev.shellcore.android.simplenotifications.source.notification.SimpleNotificationManager
import mx.dev.shellcore.android.simplenotifications.ui.theme.SimpleNotificationsTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    SimpleNotificationsTheme {
        MainLayout()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout() {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Simple Notifications")
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = { context.displayNotification() }
            ) {
                Text(text = "Trigger notification")
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = { context.displayNotificationWithAction() }
            ) {
                Text(text = "Trigger notification with action")
            }
        }
    }
}

private fun Context.displayNotification() {

    SimpleNotificationManager.createNotification(
        context = this,
        title = "Simple Notification",
        message = "This is a simple notification",
        priority = NotificationCompat.PRIORITY_HIGH,
        uri = "https://shellcore.mx/second"
    )
}

private fun Context.displayNotificationWithAction() {

    SimpleNotificationManager.createNotification(
        context = this,
        title = "Simple Notification",
        message = "This is a simple notification",
        priority = NotificationCompat.PRIORITY_HIGH,
        uri = "https://shellcore.mx/third",
        actionString = "Third",
    )
}