package mx.dev.shellcore.android.simplenotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.shellcore.android.simplenotifications.ui.screen.second.layout.SecondLayout
import mx.dev.shellcore.android.simplenotifications.ui.theme.SimpleNotificationsTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNotificationsTheme {
                SecondLayout()
            }
        }
    }
}