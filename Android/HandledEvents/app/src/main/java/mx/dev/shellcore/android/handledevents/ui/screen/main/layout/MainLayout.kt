package mx.dev.shellcore.android.handledevents.ui.screen.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.shellcore.android.handledevents.ui.theme.HandledEventsTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    HandledEventsTheme {
        MainLayout()
    }
}

@Composable
fun MainLayout() {
    var total by remember { mutableIntStateOf(0) }
    var capturedValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            style = MaterialTheme.typography.displayMedium,
            text = "Total is $total"
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = capturedValue,
            onValueChange = { capturedValue = it },
            label = { Text("Enter a number") }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Button(
                onClick = {
                    total += capturedValue.toInt()
                    capturedValue = ""
                }
            ) {
                Text("Add")
            }

            Button(
                onClick = {
                    total = 0
                    capturedValue = ""
                }
            ) {
                Text("Reset")
            }
        }
    }
}
