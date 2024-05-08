import kotlin.test.Test
import kotlin.test.assertEquals

class LongestUniqueStringTest {

    @Test
    fun evaluateFirst() {
        assertEquals(3, longestUniqueString("abcabcbb"))
    }

    @Test
    fun evaluateSecond() {
        assertEquals(1, longestUniqueString("bbbbb"))
    }

    @Test
    fun evaluateThrid() {
        assertEquals(3, longestUniqueString("pwwkew"))
    }

    @Test
    fun evaluateFourth() {
        assertEquals(2, longestUniqueString("au"))
    }
}