import kotlin.test.Test
import kotlin.test.assertEquals

class KthLargestElementTest {

    @Test
    fun test() {
        val arr = arrayOf(4, 2, 9, 7, 5, 6, 7, 1, 3)
        val k = 4
        assertEquals(6, kthLargestElement(arr, k))
    }
}