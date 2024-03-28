package mx.dev.shellcore.android.notes.ui.screen.list.layout

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import mx.dev.shellcore.android.notes.BaseLayoutTest
import mx.dev.shellcore.android.notes.ui.theme.NotesTheme
import org.junit.Test

class ListLayoutKtTest : BaseLayoutTest() {

    @Test
    fun test_001_displayScreenTitle() {
        composeTestRule.setContent {
            NotesTheme {
                ListLayout()
            }
        }

        composeTestRule.onNodeWithText("Notas").assertIsDisplayed()
    }
}