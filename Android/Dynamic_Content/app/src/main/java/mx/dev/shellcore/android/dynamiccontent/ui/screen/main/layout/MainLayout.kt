package mx.dev.shellcore.android.dynamiccontent.ui.screen.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev.shellcore.android.dynamiccontent.R
import mx.dev.shellcore.android.dynamiccontent.ui.screen.main.vm.MainViewModel

@Composable
fun MainLayout() {
    val vm: MainViewModel = hiltViewModel()

    val nameCapture = vm.nameCapture.collectAsState().value
    val nameList = vm.nameList.collectAsState().value

    MainLayoutContainer(
        nameCapture = nameCapture,
        nameList = nameList,
        onValueCaptured = {
            vm.onNameCaptureChange(it)
        },
        addName = { vm.onAddName() }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainLayoutContainer(
    nameCapture: String = "",
    nameList: List<String> = emptyList(),
    onValueCaptured: (String) -> Unit = {},
    addName: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NameCaptureLayout(
            modifier = Modifier.fillMaxWidth(),
            nameCapture = nameCapture,
            onValueCaptured = onValueCaptured,
            addName = addName
        )

        NameListLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
            nameList = nameList
        )
    }
}

@Composable
private fun NameCaptureLayout(
    modifier: Modifier = Modifier,
    nameCapture: String = "",
    onValueCaptured: (String) -> Unit = {},
    addName: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            placeholder = { Text(stringResource(R.string.main_name_placeholder)) },
            value = nameCapture,
            onValueChange = {
                onValueCaptured(it)
            }
        )

        Button(
            modifier = Modifier.wrapContentWidth(),
            onClick = {
                addName()
            }
        ) {
            Text(text = stringResource(R.string.main_add_btn))
        }
    }
}

@Composable
private fun NameListLayout(
    modifier: Modifier = Modifier,
    nameList: List<String> = emptyList()
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            nameList.forEach { name ->
                Text(text = stringResource(R.string.main_hello, name))
            }
        }
    }
}