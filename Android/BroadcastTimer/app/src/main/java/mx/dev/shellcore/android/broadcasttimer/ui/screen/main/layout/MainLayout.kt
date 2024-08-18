package mx.dev.shellcore.android.broadcasttimer.ui.screen.main.layout

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.broadcasttimer.StopWatchService
import mx.dev.shellcore.android.broadcasttimer.ui.theme.BroadcastTimerTheme
import java.util.Locale
import kotlin.math.roundToInt

@Preview
@Composable
private fun MainLayoutPreview() {
    BroadcastTimerTheme {
        MainLayout()
    }
}

@SuppressLint("NewApi")
@Composable
fun MainLayout() {

    val applicationContext = LocalContext.current

    var startedState by remember { mutableStateOf(false) }

    var time by remember { mutableDoubleStateOf(0.0) }

    val updateTime = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.displayLarge,
                text = formatTime(time)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        applicationContext.startOrStop(startedState, time)
                        startedState = !startedState
                    }
                ) {
                    Text(text = if (startedState) "Stop" else "Start")
                }
                Button(
                    onClick = {
                        applicationContext.reset()
                        time = 0.0
                    }
                ) {
                    Text(text = "Reset")
                }
            }
        }
    }

    applicationContext.registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATED_TIME),
        Context.RECEIVER_NOT_EXPORTED)
}

private fun Context.startOrStop(isStarted: Boolean, time: Double) {
    if (isStarted) {
        stop()
    } else {
        start(time)
    }
}

private fun Context.start(time: Double) {
    val serviceIntent = Intent(applicationContext, StopWatchService::class.java)
    serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
    applicationContext.startService(serviceIntent)
}

private fun Context.stop() {
    val serviceIntent = Intent(applicationContext, StopWatchService::class.java)
    applicationContext.stopService(serviceIntent)
}

private fun Context.reset() {
    stop()

}

private fun formatTime(time: Double): String {
    val timeInt = time.roundToInt()
    val hours = timeInt % 86400 / 3600
    val minutes = timeInt % 3600 / 60
    val seconds = timeInt % 60

    return String.format(Locale.ROOT, "%02d:%02d:%02d", hours, minutes, seconds)
}