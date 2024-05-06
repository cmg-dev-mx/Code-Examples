package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetNoteUseCaseImplTest : BaseUnitTest() {

    private val repository: NoteRepository = mock()

    private val id = 1
    private val expected: Note = mock()
    private val errorExpected = RuntimeException("Something went wrong.")

    @Test
    fun callGetNoteFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        uc.getNoteById(id)
        verify(repository, times(1)).getNoteById(id)
    }

    @Test
    fun getNoteByIdSuccess() = runTest {
        val uc = mockSuccessfulCase()
        assertEquals(expected, uc.getNoteById(id).first().getSuccessData())
    }

    @Test
    fun propagateErrorFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        `when`(repository.getNoteById(id)).thenReturn(flow {
            emit(RequestState.Error(errorExpected))
        })
        assertEquals(errorExpected, uc.getNoteById(id).first().getErrorException())
    }

    private suspend fun mockSuccessfulCase(): GetNoteUseCaseImpl {
        `when`(repository.getNoteById(id)).thenReturn(flow {
            emit(RequestState.Success(expected))
        })
        return GetNoteUseCaseImpl(repository)
    }
}