package mx.dev.shellcore.android.hcaptcha.ui.screen.main.layout

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.hcaptcha.HCaptchaActivity
import mx.dev.shellcore.android.hcaptcha.ui.theme.HCaptchaTheme

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

    val context = LocalContext.current
    val intent = Intent(context, HCaptchaActivity::class.java)
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data: Intent? = result.data
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                token = data?.getStringExtra("token") ?: ""
            }

            Activity.RESULT_CANCELED -> {
                token = "Error"
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = {
            launcher.launch(intent)
        }) {
            Text(text = "Validate HCaptcha")
        }

        Text(
            text = "Token: $token"
        )
    }
}