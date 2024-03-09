package tpo.lab2.trigonometricFunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.tan

class CotTest {
    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0])
    fun `test non-negative`(x: Double) {
        val cot = Cot()
        val eps = 1e-15.toBigDecimal()
        val expected = 1 / tan(x)
        val actual = cot.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [-0.5, -1.0, -1.5, -2.0, -2.5, -3.0, -3.5, -4.0, -4.5, -5.0])
    fun `test negative`(x: Double) {
        val cot = Cot()
        val eps = 1e-15.toBigDecimal()
        val expected = 1 / tan(x)
        val actual = cot.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @DisplayName("cot(PI + PIk) and cot(0) is undefined")
    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [0.0, PI, -10000*PI])
    fun `test cot(PI) and cot(0)`(x: Double) {
        val cot = Cot()
        val eps = 1e-3.toBigDecimal()
        assertThrows(ArithmeticException::class.java) {
            cot.calc(x.toBigDecimal(), eps)
        }
    }

    @DisplayName("Invalid argument")
    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `test invalid argument`(x: Double) {
        val cot = Cot()
        val eps = 1e-10.toBigDecimal()
        assertThrows(IllegalArgumentException::class.java) {
            cot.calc(x.toBigDecimal(), eps)
        }
    }

    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [100.0, 1000.0, 10000.0])
    fun `large argument numbers`(x : Double) {
        val cot = Cot()
        val eps = 1E-10.toBigDecimal()
        val expected = 1/tan(x)
        val actual = cot.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [PI/10, 2*PI/3, -1001*PI/12])
    fun `frequency of the function`(x : Double) {
        val cot = Cot()
        val eps = 1E-6.toBigDecimal()
        val expected = 1/tan(x)
        val actual = cot.calc(x.toBigDecimal() + (PI).toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [PI/10, 2*PI/3, -1001*PI/12])
    fun `parity of the function`(x : Double) {
        val cot = Cot()
        val eps = 1E-10.toBigDecimal()
        val expected = -1/tan(x)
        val actual = cot.calc(-x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [PI/2, -PI/2, 3*PI/2, -3*PI/2, 100001*PI/2])
    fun `check the vertical diameter points`(x : Double) {
        val cot = Cot()
        val eps = 1E-10.toBigDecimal()
        val expected = 0.0
        val actual = cot.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

}