package mx.dev.shellcore.android.cache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.shellcore.android.cache.ui.screen.list.layout.ListLayout
import mx.dev.shellcore.android.cache.ui.theme.CacheTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CacheTheme {
                ListLayout()
            }
        }
    }
}