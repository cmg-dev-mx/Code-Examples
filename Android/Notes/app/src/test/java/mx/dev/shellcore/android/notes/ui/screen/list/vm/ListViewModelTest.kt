package mx.dev.shellcore.android.notes.ui.screen.list.vm

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mx.dev.shellcore.android.notes.BaseUnitTest
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteListUseCase
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class ListViewModelTest : BaseUnitTest() {

    private val useCase: GetNoteListUseCase = mock()

    private val expectedList: List<Note> = mock()
    private val errorExpected = RuntimeException("Something went wrong!")

    @Test
    fun callGetListFromUseCase() = runTest {
        mockSuccessfulCase()
        verify(useCase, times(1)).getList()
    }

    @Test
    fun getLoadingStateBeforeUseCaseCall() = runTest {
        val vm = mockSuccessfulCase()
        vm.noteState.first().let {
            assert(it is RequestState.Loading)
        }
    }

    @Test
    fun getNoteListFlowFromUseCase() = runTest {
        val vm = mockSuccessfulCase()
        vm.noteState.drop(1).first().let {
            assertEquals(expectedList, it.getSuccessData())
        }
    }

    @Test
    fun propagateErrorFromUseCase() = runTest {
        `when`(useCase.getList()).thenReturn(flow {
            delay(1)
            emit(RequestState.Error(errorExpected))
        })

        val vm = ListViewModel(useCase)
        vm.noteState.drop(1).first().let {
            assertEquals(errorExpected, it.getErrorException())
        }

    }

    private suspend fun mockSuccessfulCase(): ListViewModel {
        `when`(useCase.getList()).thenReturn(flow {
            delay(1)
            emit(RequestState.Success(expectedList))
        })

        return ListViewModel(useCase)
    }
}