package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.RoundingMode

class TanTest {
    @DisplayName("Non-negative")
    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0])
    fun `test non-negative`(x: Double) {
        val tan = Tan()
        val eps = 1e-10.toBigDecimal()
        val expected = kotlin.math.tan(x)
        val actual = tan.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @DisplayName("Negative")
    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [-0.5, -1.0, -1.5, -2.0, -2.5, -3.0, -3.5, -4.0, -4.5, -5.0])
    fun `test negative`(x: Double) {
        val tan = Tan()
        val eps = 1e-10.toBigDecimal()
        val expected = kotlin.math.tan(x)
        val actual = tan.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @DisplayName("Invalid argument")
    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `test invalid argument`(x: Double) {
        val tan = Tan()
        val eps = 1e-10.toBigDecimal()
        assertThrows(IllegalArgumentException::class.java) {
            tan.calc(x.toBigDecimal(), eps)
        }
    }

    @DisplayName("tan(PI/2) is undefined")
    @Test
    fun `test tan(PI div 2)`() {
        val tan = Tan()
        val eps = 1e-3.toBigDecimal()
        val x = Math.PI.toBigDecimal().divide(2.toBigDecimal(), Math.PI.toBigDecimal().scale(), RoundingMode.HALF_EVEN)
        assertThrows(ArithmeticException::class.java) {
            tan.calc(x, eps)
        }
    }
}