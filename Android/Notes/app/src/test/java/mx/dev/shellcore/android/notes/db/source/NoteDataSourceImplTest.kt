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

    private suspend fun mockSuccessfulCase(): NoteDataSourceImpl {
        `when`(dao.queryAll()).thenReturn(daoResponse)
        `when`(mapper.toModelList(daoResponse)).thenReturn(expected)
        return NoteDataSourceImpl(dao, mapper)
    }
}