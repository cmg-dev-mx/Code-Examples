package mx.dev.shellcore.android.notes.ui.screen.list.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.dev.shellcore.android.notes.R
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.ui.route.NotesBaseRoute
import mx.dev.shellcore.android.notes.ui.screen.list.vm.ListViewModel
import mx.dev.shellcore.android.notes.ui.theme.NotesTheme
import mx.dev.shellcore.android.notes.ui.utils.DisplayResult
import mx.dev.shellcore.android.notes.ui.utils.formatShort

@Preview
@Composable
private fun ListLayoutPreview() {
    val noteState = RequestState.Success(
        listOf(
            Note(
                id = 1,
                title = "Title",
                content = "Content",
                date = System.currentTimeMillis()
            )
        )
    )
    NotesTheme {
        ListLayoutContent(noteState = noteState)
    }
}


@Composable
fun ListLayout(navController: NavController? = null) {
    val vm: ListViewModel = hiltViewModel()
    val noteState = vm.noteState.collectAsState().value

    LaunchedEffect(key1 = null) {
        vm.getNotes()
    }

    ListLayoutContent(navController, noteState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListLayoutContent(
    navController: NavController? = null,
    noteState: RequestState<List<Note>>
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
                    Text(text = stringResource(R.string.list_title))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.testTag("AddBtn"),
                onClick = {
                    navController?.navigate(NotesBaseRoute.DetailRoute.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_note)
                )
            }
        }
    ) { paddingValues ->
        noteState.DisplayResult {
            if (it.isEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.list_empty_txt))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = it) { note ->
                        NoteItem(
                            modifier = Modifier.fillMaxWidth(),
                            note = note,
                            onItemClick = {
                                navController?.navigate(
                                    NotesBaseRoute.DetailRoute.route.replace(
                                        "{id}",
                                        note.id.toString()
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onItemClick: (Note) -> Unit = {}
) {
    Card(modifier = modifier.clickable {
        onItemClick(note)
    }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                text = note.title
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium,
                text = note.date.formatShort()
            )
        }
    }
}