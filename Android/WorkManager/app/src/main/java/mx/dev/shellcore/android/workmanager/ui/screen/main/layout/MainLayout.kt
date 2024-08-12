package mx.dev.shellcore.android.workmanager.ui.screen.main.layout

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import mx.dev.shellcore.android.workmanager.MainActivity
import mx.dev.shellcore.android.workmanager.ui.theme.WorkManagerTheme
import mx.dev.shellcore.android.workmanager.worker.UploadWorker

@Preview
@Composable
private fun MainLayoutPreview() {
    WorkManagerTheme {
        MainLayout()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout() {
    val context = LocalContext.current

    var textState by remember { mutableStateOf("No state yet") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(text = "WorkManager")
                }
            )
        }
    ) { parentPadding ->
        Column(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    setOneTimeWorkRequest(context = context) {
                        textState = it
                    }
                }
            ) {
                Text(text = "Upload")
            }

            Text(
                text = textState
            )
        }
    }
}

private fun setOneTimeWorkRequest(context: Context, stateListener: (String) -> Unit) {

    val workerConstraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>()
        .setConstraints(workerConstraints)
        .build()

    val workManager = WorkManager.getInstance(context)
    workManager.enqueue(uploadRequest)
    workManager.getWorkInfoByIdLiveData(uploadRequest.id)
        .observe(context as MainActivity) { workInfo ->
            stateListener(workInfo.state.name)
        }
}