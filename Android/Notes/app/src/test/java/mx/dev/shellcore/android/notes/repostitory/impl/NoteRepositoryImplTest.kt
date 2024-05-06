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
    private val note: Note = mock()
    private val id = 1

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

    @Test
    fun callSaveNoteFromSource() = runTest {
        val repository = mockSuccessfulCase()
        val note = mock<Note>()
        repository.saveNote(note)
        verify(source, times(1)).save(note)
    }

    @Test
    fun getSuccessFromSaveNote() = runTest {
        val repository = mockSuccessfulCase()
        val actual = repository.saveNote(note).first().getSuccessData()
        assertEquals(true, actual)
    }

    @Test
    fun getErrorFromSaveNote() = runTest {
        val repository = mockSuccessfulCase()
        `when`(source.save(note)).thenThrow(exceptionExpected)
        val actual = repository.saveNote(note).first().getErrorException()
        assertEquals(exceptionExpected, actual)
    }

    @Test
    fun callGetNoteFromSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getNoteById(id)
        verify(source, times(1)).getNoteById(id)
    }

    @Test
    fun getNoteByIdFromSource() = runTest {
        val repository = mockSuccessfulCase()
        val actual = repository.getNoteById(id).first().getSuccessData()
        assertEquals(note, actual)
    }

    @Test
    fun getErrorFromGetNoteById() = runTest {
        val repository = mockSuccessfulCase()
        `when`(source.getNoteById(id)).thenThrow(exceptionExpected)
        val actual = repository.getNoteById(id).first().getErrorException()
        assertEquals(exceptionExpected, actual)
    }

    private suspend fun mockSuccessfulCase(): NoteRepositoryImpl {
        `when`(source.getList()).thenReturn(expected)
        `when`(source.save(note)).thenReturn(true)
        `when`(source.getNoteById(id)).thenReturn(note)
        return NoteRepositoryImpl(source)
    }
}