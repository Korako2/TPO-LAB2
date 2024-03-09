package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal
import kotlin.math.ln

class LnTest {
    private val DEFAULT_DIGITS_AFTER_DOT = 4
    private val DEFAULT_PRECISION = BigDecimal("1E-${DEFAULT_DIGITS_AFTER_DOT}")

    @ParameterizedTest(name = "ln({0}) for |x-1| > 1")
    @ValueSource(doubles = [2.3, 3.709, 5.0, 7.0, 10.0])
    fun `should calculate values gt 1`(x: Double) {
        val ln = Ln()
        val expected = ln(x)
        val actual = ln.calc(BigDecimal(x), DEFAULT_PRECISION)
        assertEquals(expected, actual.toDouble(), DEFAULT_PRECISION.toDouble())
    }

    @ParameterizedTest(name = "ln({0}) for |x-1| <= 1")
    @ValueSource(doubles = [1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9])
    fun `should calculate values for abs x-1 le 1`(x: Double) {
        val ln = Ln()
        val expected = ln(x)
        val actual = ln.calc(BigDecimal(x), DEFAULT_PRECISION)
        assertEquals(expected, actual.toDouble(), DEFAULT_PRECISION.toDouble())
    }

    @ParameterizedTest(name = "ln({0})")
    @ValueSource(doubles = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0])
    fun `should calculate values from 0 to 1`(x: Double) {
        val ln = Ln()
        val expected = ln(x)
        val actual = ln.calc(BigDecimal(x), DEFAULT_PRECISION)
        assertEquals(expected, actual.toDouble(), DEFAULT_PRECISION.toDouble())
    }

    @ParameterizedTest(name = "ln({0})")
    @ValueSource(doubles = [0.0, -1.0, -2.3, -3.709, -5.0, -7.0, -10.0])
    fun `should throw exception for negative and zero values`(x: Double) {
        val ln = Ln()
        assertThrows(IllegalArgumentException::class.java) {
            ln.calc(BigDecimal(x), DEFAULT_PRECISION)
        }
    }

    @DisplayName("invalid argument")
    @ParameterizedTest(name = "ln{0})")
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `test invalid argument`(x: Double) {
        val ln = Ln()
        assertThrows(IllegalArgumentException::class.java) {
            ln.calc(x.toBigDecimal(), DEFAULT_PRECISION)
        }
    }

}