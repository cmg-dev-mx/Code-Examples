package mx.dev.shellcore.android.notas.ui.screens.list.layout

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.notas.R
import mx.dev.shellcore.android.notas.ui.theme.NotasTheme

@Composable
fun ListLayout() {
    NotasTheme {
        ListLayoutContainer()
    }
}

@Preview
@Composable
private fun ListLayoutPreview() {
    NotasTheme {
        ListLayoutContainer()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListLayoutContainer() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        text = stringResource(R.string.listLayout_title)
                    )
                }
            )
        }
    ) { parentPadding ->
        Column(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize()
        ) {

        }
    }
}