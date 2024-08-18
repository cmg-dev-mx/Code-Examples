package mx.dev.shellcore.android.broadcasttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.shellcore.android.broadcasttimer.ui.route.MainNavHost
import mx.dev.shellcore.android.broadcasttimer.ui.theme.BroadcastTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BroadcastTimerTheme {
                MainNavHost()
            }
        }
    }
}