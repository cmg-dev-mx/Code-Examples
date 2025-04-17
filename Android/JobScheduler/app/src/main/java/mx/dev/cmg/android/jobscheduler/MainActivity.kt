package mx.dev.cmg.android.jobscheduler

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.cmg.android.jobscheduler.ui.route.MainNavHost
import mx.dev.cmg.android.jobscheduler.ui.theme.JobSchedulerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
