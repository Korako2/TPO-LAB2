package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FunctionSystemTest {
    private val DEFAULT_PRECISION = 0.01.toBigDecimal()

    @Test
    fun `should calculate positive values`() {
        val system = FunctionSystem()
        val x = 3.709.toBigDecimal()
        val expected = 1.57.toBigDecimal()
        val actual = system.calc(x, DEFAULT_PRECISION)
        assertEquals(expected, actual)
    }
}