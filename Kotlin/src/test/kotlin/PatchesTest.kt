import kotlin.test.Test
import kotlin.test.assertEquals

class PatchesTest {

    @Test
    fun validateTwoZones() {
        val input1 = 2
        val input2 = 2
        val input3 = arrayOf(
            intArrayOf(1,0),
            intArrayOf(0,1)
        )
        assertEquals(2, validatePatches(input1, input2, input3))
    }

    @Test
    fun validateOneZone() {
        val input1 = 2
        val input2 = 2
        val input3 = arrayOf(
            intArrayOf(1,0),
            intArrayOf(1,1)
        )
        assertEquals(1, validatePatches(input1, input2, input3))
    }

    @Test
    fun vliadateOneBigZone() {
        val input1 = 4
        val input2 = 4
        val input3 = arrayOf(
            intArrayOf(1,1,1,1),
            intArrayOf(1,0,0,1),
            intArrayOf(0,0,0,1),
            intArrayOf(1,1,1,1)
        )
        assertEquals(1, validatePatches(input1, input2, input3))
    }
}