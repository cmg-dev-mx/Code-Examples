package mx.dev.cmg.android.vertexchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.vertexchat.ui.screen.main.layout.MainLayout
import mx.dev.cmg.android.vertexchat.ui.theme.VertexChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VertexChatTheme {
                MainLayout(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize()
                )
            }
        }
    }
}