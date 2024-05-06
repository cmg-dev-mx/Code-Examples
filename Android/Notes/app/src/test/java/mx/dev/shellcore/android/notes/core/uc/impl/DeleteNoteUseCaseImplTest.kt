package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class DeleteNoteUseCaseImplTest: BaseUnitTest() {

    private val repository: NoteRepository = mock()
    private val exceptionExpected = RuntimeException("Something went wrong.")

    private val id = 1

    @Test
    fun callRepositoryToDeleteNote() = runTest {
        val uc = mockSuccessfulCase()
        uc.deleteNoteById(id)
        verify(repository, times(1)).deleteNoteById(id)
    }

    @Test
    fun deleteNoteFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        assert(uc.deleteNoteById(id).first() is RequestState.Success)
    }

    @Test
    fun propagateErrorFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        `when`(repository.deleteNoteById(id)).thenReturn(flow {
            emit(RequestState.Error(exceptionExpected))
        })
        assert(uc.deleteNoteById(id).first() is RequestState.Error)
    }

    private suspend fun mockSuccessfulCase(): DeleteNoteUseCaseImpl {
        `when`(repository.deleteNoteById(id)).thenReturn(flow {
            emit(RequestState.Success(true))
        })
        return DeleteNoteUseCaseImpl(repository)
    }
}