package mx.dev.shellcore.android.simplenotifications.ui.screen.fourth.layout

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import mx.dev.shellcore.android.simplenotifications.source.notification.SimpleNotificationManager
import mx.dev.shellcore.android.simplenotifications.ui.theme.SimpleNotificationsTheme

@Preview
@Composable
private fun FourthLayoutPreview() {
    SimpleNotificationsTheme {
        FourthLayout()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FourthLayout() {

    var name = "World"

    val context = LocalContext.current
    val activity = context.findActivity()
    val intent = activity?.intent
    intent?.let {
        val results = RemoteInput.getResultsFromIntent(it)
        if (results != null) {
            val inputString = results.getCharSequence(SimpleNotificationManager.KEY_REPLY).toString()
            name = inputString

            SimpleNotificationManager.createNotification(
                context = context,
                title = "Notification replied",
                message = "The info has been shared",
                priority = NotificationCompat.PRIORITY_HIGH
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(text = "Fourth Screen")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Hello $name")
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}