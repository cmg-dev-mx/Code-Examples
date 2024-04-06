package mx.dev.shellcore.android.notes

import org.junit.Rule

open class BaseUnitTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
}
