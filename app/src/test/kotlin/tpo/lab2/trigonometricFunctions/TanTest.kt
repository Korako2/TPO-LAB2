package tpo.lab2.trigonometricFunctions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.tan

class TanTest {
    @DisplayName("Non-negative")
    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0])
    fun `test non-negative`(x: Double) {
        val tan = Tan()
        val eps = 1e-10.toBigDecimal()
        val expected = tan(x)
        val actual = tan.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @DisplayName("Negative")
    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [-0.5, -1.0, -1.5, -2.0, -2.5, -3.0, -3.5, -4.0, -4.5, -5.0])
    fun `test negative`(x: Double) {
        val tan = Tan()
        val eps = 1e-10.toBigDecimal()
        val expected = tan(x)
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

    @DisplayName("tan(PI/2 + PIk) is undefined")
    @ParameterizedTest
    @ValueSource(doubles = [PI/2, -PI/2, 75*PI/2])
    fun `test tan(PI div 2)`(x: Double) {
        val tan = Tan()
        val eps = 1e-3.toBigDecimal()
        assertThrows(ArithmeticException::class.java) {
            tan.calc(x.toBigDecimal(), eps)
        }
    }

    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [0.0, 2*PI/3, -2*PI, -1001*PI/4])
    fun `frequency of the function`(x : Double) {
        val tan = Tan()
        val eps = 1E-10.toBigDecimal()
        val expected = tan(x)
        val actual = tan.calc(x.toBigDecimal() + (PI).toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "tan({0})")
    @ValueSource(doubles = [0.0, 2*PI/3, -2*PI/6, -1001*PI/4])
    fun `parity of the function`(x : Double) {
        val tan = Tan()
        val eps = 1E-10.toBigDecimal()
        val expected = -tan(x)
        val actual = tan.calc(-x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

}