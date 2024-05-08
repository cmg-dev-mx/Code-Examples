import kotlin.test.Test
import kotlin.test.assertEquals

class ValidIpsTest {
    
    @Test
    fun validateIp1() {
        val s = "1111"
        val expected = listOf("1.1.1.1")
        assertEquals(expected, validIps(s))
    }

    @Test
    fun validateIp2() {
        val s = "25525511135"
        val expected = listOf("255.255.11.135", "255.255.111.35")
        assertEquals(expected, validIps(s))
    }

    @Test
    fun validateIp3() {
        val s = "25505011535"
        val expected = emptyList<String>()
        assertEquals(expected, validIps(s))
    }
}    