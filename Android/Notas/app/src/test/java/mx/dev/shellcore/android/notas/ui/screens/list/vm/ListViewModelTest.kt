package mx.dev.shellcore.android.notas.ui.screens.list.vm

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notas.base.BaseUnitTest
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.model.request.RequestState
import mx.dev.shellcore.android.notas.core.uc.base.GetNotesUseCase
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest : BaseUnitTest() {

    private val getNotesUseCase: GetNotesUseCase = mock()
    private val list = listOf(Note(1, "Title", "Description", 0))
    private val emptyList = emptyList<Note>()
    private val exception = Exception("Error getting notes")

    @Test
    fun callGetNotesUseCase() = runTest {
        mockSuccessfulCase()
        advanceUntilIdle()
        verify(getNotesUseCase, times(1)).getNotes()
    }

    @Test
    fun showLoading() = runTest {
        val vm = mockSuccessfulCase()
        vm.notes.first().let {
            assertEquals(RequestState.Loading, it)
        }
    }

    @Test
    fun getEmptyListFromUseCase() = runTest {
        `when`(getNotesUseCase.getNotes()).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(emptyList))
        })
        val vm = ListViewModel(getNotesUseCase)
        vm.notes.drop(1).first().let {
            assertEquals(RequestState.Success(emptyList), it)
        }
    }

    @Test
    fun getSuccessFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        vm.notes.drop(1).first().let {
            assertEquals(RequestState.Success(list), it)
        }
    }

    @Test
    fun getErrorFromUseCase() = runTest {
        val vm = mockFailureCase()
        vm.notes.drop(1).first().let {
            assertEquals(RequestState.Error(exception), it)
        }
    }

    private suspend fun mockFailureCase(): ListViewModel {
        `when`(getNotesUseCase.getNotes()).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(exception))
        })
        return ListViewModel(getNotesUseCase)
    }


    private suspend fun mockSuccessfulCase(): ListViewModel {
        `when`(getNotesUseCase.getNotes()).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(list))
        })
        return ListViewModel(getNotesUseCase)
    }
}