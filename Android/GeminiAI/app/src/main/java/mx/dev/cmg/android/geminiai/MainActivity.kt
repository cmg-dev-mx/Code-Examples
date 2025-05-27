package mx.dev.cmg.android.geminiai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.cmg.android.geminiai.ui.screen.main.layout.MainLayout
import mx.dev.cmg.android.geminiai.ui.theme.GeminiAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeminiAITheme {
                MainLayout()
            }
        }
    }
}
