package mx.dev.shellcore.android.notas.base

import org.junit.Rule

open class BaseUnitTest {

    @get:Rule
    var coroutineScope = MainCoroutineScopeRule()
}
