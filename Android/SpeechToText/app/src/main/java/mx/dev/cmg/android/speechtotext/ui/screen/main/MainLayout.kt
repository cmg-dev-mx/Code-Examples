package mx.dev.cmg.android.speechtotext.ui.screen.main

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import mx.dev.cmg.android.speechtotext.R
import mx.dev.cmg.android.speechtotext.ui.theme.SpeechToTextTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    SpeechToTextTheme {
        MainLayout()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainLayout(modifier: Modifier = Modifier) {
    // Permisos
    var permissionState by remember { mutableStateOf("Pendiente") }

    val recordAudioPermissionState = rememberPermissionState(android.Manifest.permission.RECORD_AUDIO)
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            permissionState = "Permiso concedido"
        } else {
            permissionState = "Permiso denegado"
        }
    }

    LaunchedEffect(null) {
        if (recordAudioPermissionState.hasPermission) {
            permissionState = "Permiso concedido"
        }
    }

    // Speech Recognizer
    var speechText by remember { mutableStateOf("") }
    var listening by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, java.util.Locale.getDefault())

    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    speechRecognizer.setRecognitionListener(object : android.speech.RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            listening = true
        }

        override fun onBeginningOfSpeech() {}

        override fun onRmsChanged(rmsdB: Float) {}

        override fun onBufferReceived(buffer: ByteArray?) {}

        override fun onEndOfSpeech() {
            listening = false
        }

        override fun onError(error: Int) {
            speechText = "Error: $error"
        }

        override fun onResults(results: Bundle?) {
            val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            speechText = data?.get(0) ?: ""
        }

        override fun onPartialResults(partialResults: Bundle?) {}

        override fun onEvent(eventType: Int, params: Bundle?) {}
    })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = speechText
        )

        Button(
            modifier = Modifier.wrapContentWidth().padding(top = 16.dp),
            onClick = {
                if (recordAudioPermissionState.hasPermission) {
                    if (listening) {
                        speechRecognizer.stopListening()
                    } else {
                        speechRecognizer.startListening(intent)
                    }
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                }
            }
        ) {
            Icon(
               imageVector = ImageVector.vectorResource(if (listening) R.drawable.ic_mic_on else R.drawable.ic_mic_off),
                contentDescription = null,
                tint = if (listening) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            modifier = Modifier.wrapContentWidth().padding(top = 32.dp),
            textAlign = TextAlign.Center,
            text = permissionState
        )
    }

}