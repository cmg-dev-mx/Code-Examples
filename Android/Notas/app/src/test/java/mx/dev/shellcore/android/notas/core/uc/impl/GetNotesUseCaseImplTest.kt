package mx.dev.shellcore.android.notas.core.uc.impl

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notas.base.BaseUnitTest
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.repository.base.NoteRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetNotesUseCaseImplTest : BaseUnitTest() {

    private val repository: NoteRepository = mock()
    private val responseExpected = listOf<Note>()
    private val exceptionExpected = Exception("Something went wrong")

    @Test
    fun callRepositoryToGetNotes() = runTest {
        val uc = mockSuccessfulCase()
        uc.getNotes()
        verify(repository, times(1)).getAllNotes()
    }

    @Test
    fun getAllNotesFromRepository() = runTest {
        val uc = mockSuccessfulCase()
        uc.getNotes().collect {
            val response = it.getSuccessData()
            assertEquals(responseExpected, response)
        }
    }

    @Test
    fun getNotesFromRepositoryError() = runTest {
        `when`(repository.getAllNotes()).thenReturn(flow {
            throw exceptionExpected
        })
        val uc = GetNotesUseCaseImpl(repository)
        uc.getNotes().collect {
            val response = it.getErrorData()
            assertEquals(exceptionExpected, response)
        }
    }

    private suspend fun mockSuccessfulCase(): GetNotesUseCaseImpl {
        `when`(repository.getAllNotes()).thenReturn(flow {
            emit(responseExpected)
        })
        return GetNotesUseCaseImpl(repository)
    }
}