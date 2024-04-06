package mx.dev.shellcore.android.notes.repostitory.impl

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NoteRepositoryImplTest : BaseUnitTest() {

    private val source: NoteDataSource = mock()

    private val expected: List<Note> = mock()
    private val exceptionExpected = RuntimeException("Something went wrong")

    @Test
    fun callGetListFromSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getList()
        verify(source, times(1)).getList()
    }

    @Test
    fun getLNoteListFromSource() = runTest {
        val repository = mockSuccessfulCase()
        val actual = repository.getList().first().getSuccessData()
        assertEquals(expected, actual)
    }

    @Test
    fun getErrorFromSource() = runTest {
        val repository = mockSuccessfulCase()
        `when`(source.getList()).thenThrow(exceptionExpected)
        val actual = repository.getList().first().getErrorException()
        assertEquals(exceptionExpected, actual)
    }

    private suspend fun mockSuccessfulCase(): NoteRepositoryImpl {
        `when`(source.getList()).thenReturn(expected)
        return NoteRepositoryImpl(source)
    }
}