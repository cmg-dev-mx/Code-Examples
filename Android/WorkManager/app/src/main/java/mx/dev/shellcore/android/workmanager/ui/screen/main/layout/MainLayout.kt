package mx.dev.shellcore.android.workmanager.ui.screen.main.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.workmanager.ui.theme.WorkManagerTheme

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
                .padding(16.dp)
        ) {
            Text(
                text = "Hello WorkManager!",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}