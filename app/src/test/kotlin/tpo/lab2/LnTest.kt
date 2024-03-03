package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class LnTest {
    private val DEFAULT_DIGITS_AFTER_DOT = 4
    private val DEFAULT_PRECISION = BigDecimal("1E-${DEFAULT_DIGITS_AFTER_DOT}")

    @Test
    fun testRandomValues() {
        val ln = Ln()
        val expected = BigDecimal("0.6931")
        assertEquals(expected, ln.calc(BigDecimal("2"), DEFAULT_PRECISION))
    }

    @Test
    fun testSmallValues() {
        val ln = Ln()
        val expected = BigDecimal("-0.6931")
        assertEquals(expected, ln.calc(BigDecimal("0.5"), DEFAULT_PRECISION))
    }

    @Test
    fun calcOne() {
        val ln = Ln()
        val expected = BigDecimal.ZERO
        assertEquals(expected, ln.calc(BigDecimal.ONE, DEFAULT_PRECISION))
    }

    @Test
    fun calcZero() {
        val ln = Ln()
        assertThrows(IllegalArgumentException::class.java) {
            ln.calc(BigDecimal.ZERO, DEFAULT_PRECISION)
        }
    }

    @Test
    fun calcNegative() {
        val ln = Ln()
        assertThrows(IllegalArgumentException::class.java) {
            ln.calc(BigDecimal("-1"), DEFAULT_PRECISION)
        }
    }
}