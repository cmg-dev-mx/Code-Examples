package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SaveNoteUseCaseImplTest : BaseUnitTest() {

    private val repository = mock(NoteRepository::class.java)

    private val note: Note = mock()
    private val exceptionExpected = RuntimeException("Something went wrong.")

    @Test
    fun callSaveNoteFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        uc.saveNote(note)
        verify(repository, times(1)).saveNote(note)
    }

    @Test
    fun saveNoteFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        assert(uc.saveNote(note).first() is RequestState.Success)
    }

    @Test
    fun errorFromRepository() = runTest {
        `when`(repository.saveNote(note)).thenReturn(flow {
            emit(RequestState.Error(exceptionExpected))
        })
        val uc = SaveNoteUseCaseImpl(repository)
        assert(uc.saveNote(note).first() is RequestState.Error)
    }

    private suspend fun mockSuccessfulCase(): SaveNoteUseCaseImpl {
        `when`(repository.saveNote(note)).thenReturn(flow {
            emit(RequestState.Success(true))
        })
        return SaveNoteUseCaseImpl(repository)
    }
}