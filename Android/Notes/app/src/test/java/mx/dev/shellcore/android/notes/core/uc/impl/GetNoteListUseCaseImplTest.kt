package mx.dev.shellcore.android.notes.core.uc.impl

import junit.framework.TestCase.assertEquals
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

class GetNoteListUseCaseImplTest : BaseUnitTest() {

    private val repository: NoteRepository = mock()
    private val expected: List<Note> = mock()
    private val errorExpected = RuntimeException("Something went wrong.")

    @Test
    fun callGetListFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        uc.getList()
        verify(repository, times(1)).getList()
    }

    @Test
    fun getListFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        assertEquals(expected, uc.getList().first().getSuccessData())
    }

    @Test
    fun propagateErrorFromRepository() = runTest {
        `when`(repository.getList()).thenReturn(flow {
            emit(RequestState.Error(errorExpected))
        })
        val uc = GetNoteListUseCaseImpl(repository)
        assertEquals(errorExpected, uc.getList().first().getErrorException())
    }

    private suspend fun mockSuccessfulCase(): GetNoteListUseCaseImpl {
        `when`(repository.getList()).thenReturn(flow {
            emit(RequestState.Success(expected))
        })
        return GetNoteListUseCaseImpl(repository)
    }
}