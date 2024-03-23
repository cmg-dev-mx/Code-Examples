package mx.dev.shellcore.android.notas

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import mx.dev.shellcore.android.notas.base.BaseLayoutTest
import mx.dev.shellcore.android.notas.ui.screens.list.layout.ListLayout
import mx.dev.shellcore.android.notas.ui.theme.NotasTheme
import org.junit.Test

class ListLayoutTest : BaseLayoutTest() {

    @Test
    fun test_001_displayScreenTitle() {
        composeTestRule.setContent {
            NotasTheme {
                ListLayout()
            }
        }

        composeTestRule.onNodeWithText("Notas").assertIsDisplayed()
    }
}