package mx.dev.shellcore.android.notes.ui.screen.list.layout

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import mx.dev.shellcore.android.notes.BaseLayoutTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.ui.theme.NotesTheme
import org.junit.Test
import java.util.Calendar

class ListLayoutKtTest : BaseLayoutTest() {

    @Test
    fun test_001_displayScreenTitle() {
        composeTestRule.setContent {
            NotesTheme {
                ListLayoutContent(RequestState.Success(emptyList()))
            }
        }

        composeTestRule.onNodeWithText("Notas").assertIsDisplayed()
    }

    @Test
    fun text_002_displayEmptyListMessage() {
        composeTestRule.setContent {
            NotesTheme {
                ListLayoutContent(RequestState.Success(emptyList()))
            }
        }

        composeTestRule.onNodeWithText("Sin notas").assertIsDisplayed()
    }

    @Test
    fun text_003_displayList() {
        val customDate = Calendar.getInstance().apply {
            set(2024, 7, 14)
        }.timeInMillis

        val notes = listOf(
            Note(
                id = 1,
                title = "Title",
                content = "Content",
                date = customDate
            )
        )

        composeTestRule.setContent {
            NotesTheme {
                ListLayoutContent(RequestState.Success(notes))
            }
        }

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Aug 14").assertIsDisplayed()
    }
}