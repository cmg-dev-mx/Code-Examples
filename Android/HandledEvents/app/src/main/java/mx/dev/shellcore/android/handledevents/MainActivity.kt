package mx.dev.shellcore.android.handledevents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.shellcore.android.handledevents.ui.screen.main.layout.MainLayout
import mx.dev.shellcore.android.handledevents.ui.theme.HandledEventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandledEventsTheme {
                MainLayout()
            }
        }
    }
}