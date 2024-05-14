package mx.dev.shellcore.android.hcaptcha.ui.screen.main.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.hcaptcha.ui.theme.HCaptchaTheme
import kotlin.random.Random

@Preview
@Composable
private fun MainLayoutPreview() {
    HCaptchaTheme {
        MainLayout()
    }
}

@Composable
fun MainLayout() {
    var token by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = {
            token = Random.nextInt(0, 100).toString()
        }) {
            Text(text = "Validate HCaptcha")
        }

        Text(
            text = "Token: $token"
        )
    }
}