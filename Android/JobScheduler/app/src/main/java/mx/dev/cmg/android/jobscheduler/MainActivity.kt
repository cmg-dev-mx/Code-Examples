package mx.dev.cmg.android.jobscheduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.cmg.android.jobscheduler.ui.route.MainNavHost
import mx.dev.cmg.android.jobscheduler.ui.theme.JobSchedulerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobSchedulerTheme {
                MainNavHost()
            }
        }
    }
}
