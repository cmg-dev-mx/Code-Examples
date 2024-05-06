package mx.dev.shellcore.android.notes.ui.screen.detail.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.dev.shellcore.android.notes.R
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.ui.screen.detail.vm.DetailViewModel
import mx.dev.shellcore.android.notes.ui.theme.NotesTheme
import java.util.Calendar

@Preview
@Composable
private fun DetailLayoutPreview() {
    NotesTheme {
        DetailLayoutContent()
    }
}

@Composable
fun DetailLayout(
    navController: NavController? = null,
    id: Int = 0
) {
    val vm: DetailViewModel = hiltViewModel()
    val note = vm.note.collectAsState().value
    val saveSuccess = vm.noteSavedState.collectAsState().value

    if (id != 0) {
        LaunchedEffect(key1 = null) { vm.getNoteById(id) }
    }

    if (saveSuccess.getSuccessData() == true) {
        LaunchedEffect(key1 = null) {
            vm.updateNoteSavedState(false)
            navController?.popBackStack()
        }
    }

    DetailLayoutContent(
        note = note,
        onTitleChange = { vm.setNoteTitle(it) },
        onContentChange = { vm.setNoteContent(it) },
        onClickBtnSave = {
            vm.saveNote(note, Calendar.getInstance().timeInMillis)
        },
        onClickBtnBack = { navController?.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLayoutContent(
    note: Note = Note(),
    onTitleChange: (String) -> Unit = {},
    onContentChange: (String) -> Unit = {},
    onClickBtnSave: () -> Unit = {},
    onClickBtnBack: () -> Unit = {}
) {

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
                    Text(text = "Detalle de Nota")
                },
                navigationIcon = {
                    IconButton(onClick = { onClickBtnBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.labelMedium
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(text = stringResource(R.string.title_placeholder))
                },
                value = note.title,
                onValueChange = { onTitleChange(it) }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = stringResource(R.string.content),
                style = MaterialTheme.typography.labelMedium
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                minLines = 5,
                placeholder = {
                    Text(text = stringResource(R.string.content_placeholder))
                },
                value = note.content,
                onValueChange = { onContentChange(it) }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClickBtnSave() }
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}