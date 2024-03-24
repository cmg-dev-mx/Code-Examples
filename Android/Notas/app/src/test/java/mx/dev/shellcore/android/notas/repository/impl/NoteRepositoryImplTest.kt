package mx.dev.shellcore.android.notas.repository.impl

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notas.base.BaseUnitTest
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.repository.source.DataBaseSource
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NoteRepositoryImplTest : BaseUnitTest() {

    private val responseExpected: List<Note> = mock()
    private val errorExpected = RuntimeException("Something went wrong")
    private val dbSource: DataBaseSource = mock()

    @Test
    fun callSourceWhenQueryAllNotes() = runTest {
        val repository = mockSuccessfulCase()
        repository.getAllNotes().collect {
            verify(dbSource, times(1)).getNotes()
        }
    }

    @Test
    fun getNotesFromSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getAllNotes().collect {
            assertEquals(responseExpected, it)
        }
    }

    @Test
    fun propagateErrors() = runTest {
        `when`(dbSource.getNotes()).thenThrow(errorExpected)
        val repository = NoteRepositoryImpl(dbSource)
        repository.getAllNotes().catch {
            assertEquals(errorExpected, it)
        }.collect {}
    }

    private suspend fun mockSuccessfulCase(): NoteRepositoryImpl {
        `when`(dbSource.getNotes()).thenReturn(responseExpected)
        return NoteRepositoryImpl(dbSource)
    }
}