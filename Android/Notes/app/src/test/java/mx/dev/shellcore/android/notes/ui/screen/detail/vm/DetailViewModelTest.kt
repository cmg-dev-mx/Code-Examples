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
import mx.dev.shellcore.android.notes.core.uc.base.DeleteNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Calendar

class DetailViewModelTest : BaseUnitTest() {

    private val saveNoteUseCase: SaveNoteUseCase = mock()
    private val getNoteUseCase: GetNoteUseCase = mock()
    private val deleteNoteUseCase: DeleteNoteUseCase = mock()

    private val note: Note = mock()
    private val errorExpected = RuntimeException("Something went wrong!")

    @Test
    fun callSaveNoteFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        vm.saveNote(note, Calendar.getInstance().timeInMillis)
        verify(saveNoteUseCase, times(1)).saveNote(note)
    }

    @Test
    fun getSuccessInSaveNote() = runTest {
        val vm = mockSuccessfulCase()
        vm.saveNote(note, Calendar.getInstance().timeInMillis)
        vm.noteSavedState.drop(1).first().let {
            assertTrue(it.getSuccessData() ?: false)
        }
    }

    @Test
    fun propagateErrorFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        `when`(saveNoteUseCase.saveNote(note)).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(errorExpected))
        })
        vm.saveNote(note, Calendar.getInstance().timeInMillis)
        vm.noteSavedState.drop(1).first().let {
            assertEquals(errorExpected, it.getErrorException())
        }
    }

    @Test
    fun updateNoteTitle() = runTest {
        val vm = mockSuccessfulCase()
        val title = "Title"
        vm.setNoteTitle(title)
        vm.note.first().let {
            assertEquals(title, it.title)
        }
    }

    @Test
    fun updateNoteContent() = runTest {
        val vm = mockSuccessfulCase()
        val content = "Content"
        vm.setNoteContent(content)
        vm.note.first().let {
            assertEquals(content, it.content)
        }
    }

    @Test
    fun updateNoteDate() = runTest {
        val vm = mockSuccessfulCase()
        val date = 123456789L
        vm.setNoteDate(date)
        vm.note.first().let {
            assertEquals(date, it.date)
        }
    }

    @Test
    fun updateNoteSavedState() = runTest {
        val vm = mockSuccessfulCase()
        val value = true
        vm.updateNoteSavedState(value)
        vm.noteSavedState.first().let {
            assertTrue(it.getSuccessData() ?: false)
        }
    }

    @Test
    fun callGetNoteByIdFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        vm.getNoteById(id)
        verify(getNoteUseCase, times(1)).getNoteById(id)
    }

    @Test
    fun getNoteById() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        vm.getNoteById(id)
        vm.note.drop(1).first().let {
            assertEquals(note, it)
        }
    }

    @Test
    fun propagateErrorFromGetNoteById() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        `when`(getNoteUseCase.getNoteById(id)).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(errorExpected))
        })
        vm.getNoteById(id)
        vm.noteLoadedState.drop(1).first().let {
            assertEquals(errorExpected, it.getErrorException())
        }
    }

    @Test
    fun callDeleteNoteFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        vm.deleteNoteById(id)
        verify(deleteNoteUseCase, times(1)).deleteNoteById(id)
    }

    @Test
    fun getSuccessInDeleteNote() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        vm.deleteNoteById(id)
        vm.noteSavedState.drop(1).first().let {
            assertTrue(it.getSuccessData() ?: false)
        }
    }

    @Test
    fun propagateErrorFromDeleteNote() = runTest {
        val vm = mockSuccessfulCase()
        val id = 1
        `when`(deleteNoteUseCase.deleteNoteById(id)).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(errorExpected))
        })
        vm.deleteNoteById(id)
        vm.noteSavedState.drop(1).first().let {
            assertEquals(errorExpected, it.getErrorException())
        }
    }

    private suspend fun mockSuccessfulCase(): DetailViewModel {
        `when`(saveNoteUseCase.saveNote(note)).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(true))
        })
        `when`(getNoteUseCase.getNoteById(1)).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(note))
        })
        `when`(deleteNoteUseCase.deleteNoteById(1)).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(true))
        })
        return DetailViewModel(saveNoteUseCase, getNoteUseCase, deleteNoteUseCase)
    }
}