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
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import mx.dev.shellcore.android.workmanager.MainActivity
import mx.dev.shellcore.android.workmanager.ui.theme.WorkManagerTheme
import mx.dev.shellcore.android.workmanager.worker.CompressWorker
import mx.dev.shellcore.android.workmanager.worker.DownloadWorker
import mx.dev.shellcore.android.workmanager.worker.FilterWorker
import mx.dev.shellcore.android.workmanager.worker.RepeatWorker
import mx.dev.shellcore.android.workmanager.worker.UploadWorker
import mx.dev.shellcore.android.workmanager.worker.UploadWorker.Companion.KEY_DATA
import java.util.concurrent.TimeUnit

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
    val applicationContext = context.applicationContext

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

            Button(
                onClick = {
                    setPeriodicWorkRequest(context = applicationContext)
                }
            ) {
                Text(text = "Periodic")
            }

            Text(
                text = textState
            )
        }
    }
}

private fun setOneTimeWorkRequest(context: Context, stateListener: (String) -> Unit) {

    val data = Data.Builder()
        .putString(KEY_DATA, "This is the content to upload")
        .build()

    val workerConstraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>()
        .setConstraints(workerConstraints)
        .setInputData(data)
        .build()

    val filterRequest = OneTimeWorkRequestBuilder<FilterWorker>()
        .build()

    val compressRequest = OneTimeWorkRequestBuilder<CompressWorker>()
        .build()

    val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
        .build()

    val parallelWork = mutableListOf<OneTimeWorkRequest>() // Parallel work

    parallelWork.add(downloadRequest)
    parallelWork.add(filterRequest)

    val workManager = WorkManager.getInstance(context)

    workManager.beginWith(parallelWork) // Chain work
        .then(compressRequest)
        .then(uploadRequest)
        .enqueue()

    workManager.getWorkInfoByIdLiveData(uploadRequest.id)
        .observe(context as MainActivity) { workInfo ->
            stateListener(workInfo.state.name)

            if (workInfo.state.isFinished) {
                val result = workInfo.outputData.getString(UploadWorker.KEY_RESULT)
                stateListener(result ?: "No data")
            }
        }
}

private fun setPeriodicWorkRequest(context: Context) {
    val periodicRequest = PeriodicWorkRequestBuilder<RepeatWorker>(
        PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
        TimeUnit.MILLISECONDS
    ).build()

    WorkManager.getInstance(context)
        .enqueue(periodicRequest)
}