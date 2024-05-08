import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class AnagramsTest {
 
    @Test
    fun testAnagramsValid() {
        val s1 = "garden"
        val s2 = "danger"
        assertTrue(areAnagrams(s1, s2))
    }

    @Test
    fun testAnagramsValid2() {
        val s1 = "salesmen"
        val s2 = "nameless"
        assertTrue(areAnagrams(s1, s2))
    }

    @Test
    fun testAnagramsInvalid() {
        val s1 = "coca"
        val s2 = "calo"
        assertFalse(areAnagrams(s1, s2))
    }

    @Test
    fun testAnagramsValidSort() {
        val s1 = "garden"
        val s2 = "danger"
        assertTrue(areAnagramsSort(s1, s2))
    }

    @Test
    fun testAnagramsValid2Sort() {
        val s1 = "salesmen"
        val s2 = "nameless"
        assertTrue(areAnagramsSort(s1, s2))
    }

    @Test
    fun testAnagramsInvalidSort() {
        val s1 = "coca"
        val s2 = "calo"
        assertFalse(areAnagramsSort(s1, s2))
    }
}