package mx.dev.shellcore.android.composesnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import mx.dev.shellcore.android.composesnackbar.ui.screen.main.layout.MainLayout
import mx.dev.shellcore.android.composesnackbar.ui.theme.ComposeSnackbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeSnackbarTheme {
                MainLayout()
            }
        }
    }
}