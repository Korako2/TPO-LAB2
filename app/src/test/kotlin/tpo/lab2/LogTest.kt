package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LogTest {
    @Test
    fun `log(2, 0) throws Exception`() {
        val base = 2
        val log = Log(base)
        val x = 0
        val eps = 0
        assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }

    @Test
    fun `log(2, 1) is 0`() {
        val base = 2
        val log = Log(base)
        val x = 1
        val eps = 1
        assertEquals(0.toBigDecimal(), log.calc(x.toBigDecimal(), eps.toBigDecimal()))
    }

    @Test
    fun `log(2, NaN) throws Exception`() {
        val base = 2
        val log = Log(base)
        val x = Double.NaN
        val eps = Double.NaN
        assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }
}