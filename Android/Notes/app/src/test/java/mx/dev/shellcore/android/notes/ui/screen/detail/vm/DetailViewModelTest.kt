package mx.dev.shellcore.android.notes.ui.screen.detail.vm

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class DetailViewModelTest : BaseUnitTest() {

    private val useCase: SaveNoteUseCase = mock()

    private val note: Note = mock()
    private val errorExpected = RuntimeException("Something went wrong!")

    @Test
    fun callSaveNoteFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        vm.saveNote(note)
        verify(useCase, times(1)).saveNote(note)
    }

    @Test
    fun getSuccessInSaveNote() = runTest {
        val vm = mockSuccessfulCase()
        vm.saveNote(note)
        vm.noteState.drop(1).first().let {
            assertTrue(it.getSuccessData() ?: false)
        }
    }

    @Test
    fun propagateErrorFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        `when`(useCase.saveNote(note)).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(errorExpected))
        })
        vm.saveNote(note)
        vm.noteState.drop(1).first().let {
            assertEquals(errorExpected, it.getErrorException())
        }
    }

    private suspend fun mockSuccessfulCase(): DetailViewModel {
        `when`(useCase.saveNote(note)).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(true))
        })
        return DetailViewModel(useCase)
    }
}