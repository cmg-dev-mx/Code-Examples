import kotlin.test.Test
import kotlin.test.assertEquals

class SherlokHolmesAnagramTest {
    
    @Test
    fun validateSmall() {
        val evaluation = "abba"
        val expected = 4

        /**
         * [a,b,b,a] -> 2 (a, b)
         * [ab, bb, ba] -> 1 (ab)
         * [abb, bba] -> 1 (abb)
         */

         assertEquals(expected, sherlokHolmesAnagram(evaluation))
    }

    @Test
    fun validateSmall2() {
        val evaluation = "ifailuhkqq"
        val expected = 3

        assertEquals(expected, sherlokHolmesAnagram(evaluation))
    }

    @Test
    fun validateSmall3() {
        val evaluation = "kkkk"
        val expected = 10

        assertEquals(expected, sherlokHolmesAnagram(evaluation))
    }
}