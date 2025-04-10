package mx.dev.cmg.android.jobscheduler.ui.layout.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.jobscheduler.ui.theme.JobSchedulerTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    JobSchedulerTheme {
        MainLayoutScreen()
    }
}

@Composable
fun MainLayout() {

    MainLayoutScreen()
}

@Composable
private fun MainLayoutScreen(
    onLogin: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Job Scheduler", style = MaterialTheme.typography.headlineMedium)

        Button(
            modifier = Modifier.wrapContentWidth(),
            onClick = onLogin,
        ) {
            Text("Login")
        }
    }
}