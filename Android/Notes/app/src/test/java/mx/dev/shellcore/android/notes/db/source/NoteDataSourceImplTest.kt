package mx.dev.shellcore.android.notes.db.source

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.db.dao.NoteDao
import mx.dev.shellcore.android.notes.db.mapper.NoteMapper
import mx.dev.shellcore.android.notes.db.model.NoteDO
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NoteDataSourceImplTest : BaseUnitTest() {

    private val dao: NoteDao = mock()
    private val mapper: NoteMapper = mock()
    private val expected: List<Note> = mock()
    private val daoResponse: List<NoteDO> = mock()
    private val exceptionExpected = RuntimeException("Something went wrong")
    private val note: Note = mock()
    private val noteDO: NoteDO = mock()
    private val id = 1

    @Test
    fun callNotesFromDao() = runTest {
        val source = mockSuccessfulCase()
        source.getList()
        verify(dao, times(1)).queryAll()
    }

    @Test
    fun getNotesFromDao() = runTest {
        val source = mockSuccessfulCase()
        assertEquals(expected, source.getList())
    }

    @Test
    fun propagateErrorsFromMapper() = runTest {
        val source = mockSuccessfulCase()
        `when`(mapper.toModelList(daoResponse)).thenThrow(exceptionExpected)
        val result = kotlin.runCatching { source.getList() }
        assertEquals(exceptionExpected, result.exceptionOrNull())
    }

    @Test
    fun propagateErrorsFromDao() = runTest {
        val source = mockSuccessfulCase()
        `when`(dao.queryAll()).thenThrow(exceptionExpected)
        val result = kotlin.runCatching { source.getList() }
        assertEquals(exceptionExpected, result.exceptionOrNull())
    }

    @Test
    fun callSaveNoteFromDao() = runTest {
        val source = mockSuccessfulCase()
        source.save(note)
        verify(dao, times(1)).insert(noteDO)
    }

    @Test
    fun saveNoteFromDao() = runTest {
        val source = mockSuccessfulCase()
        assertEquals(true, source.save(note))
    }

    @Test
    fun propagateErrorsFromSaveNote() = runTest {
        val source = mockSuccessfulCase()
        `when`(dao.insert(noteDO)).thenThrow(exceptionExpected)
        val result = kotlin.runCatching { source.save(note) }
        assertEquals(exceptionExpected, result.exceptionOrNull())
    }

    @Test
    fun callGetNoteByIdFromDao() = runTest {
        val source = mockSuccessfulCase()
        source.getNoteById(id)
        verify(dao, times(1)).queryById(id)
    }

    @Test
    fun getNoteByIdFromDao() = runTest {
        val source = mockSuccessfulCase()
        source.getNoteById(id)
        val result = kotlin.runCatching { source.getNoteById(id) }
        assertEquals(note, result.getOrNull())
    }

    @Test
    fun propagateErrorsFromGetNoteById() = runTest {
        val source = mockSuccessfulCase()
        `when`(dao.queryById(id)).thenThrow(exceptionExpected)
        val result = kotlin.runCatching { source.getNoteById(id) }
        assertEquals(exceptionExpected, result.exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase(): NoteDataSourceImpl {
        `when`(dao.queryAll()).thenReturn(daoResponse)
        `when`(mapper.toModelList(daoResponse)).thenReturn(expected)
        `when`(mapper.toModel(noteDO)).thenReturn(note)
        `when`(mapper.toDataObject(note)).thenReturn(noteDO)
        `when`(dao.queryById(id)).thenReturn(noteDO)
        return NoteDataSourceImpl(dao, mapper)
    }
}