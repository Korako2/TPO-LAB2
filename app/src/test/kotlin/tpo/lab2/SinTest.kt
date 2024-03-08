package tpo.lab2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.sin

class SinTest {
    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [0.0, 1.0, 2.0, 2*PI/3])
    fun `non-negative numbers`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = sin(x)
        val actual = sin.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [-1.0, -2.0, -3.0])
    fun `negative numbers`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = sin(x)
        val actual = sin.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [100.0, 1000.0, 10000.0, 100000*PI])
    fun `large argument numbers`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = sin(x)
        val actual = sin.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [0.0, 2*PI/3, -2*PI, -1001*PI/2])
    fun `frequency of the function`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = sin(x)
        val actual = sin.calc(x.toBigDecimal() + (2*PI).toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [0.0, 2*PI/3, -2*PI/6, -1001*PI/2])
    fun `parity of the function`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = -sin(x)
        val actual = sin.calc(-x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `test invalid argument`(x: Double) {
        val sin = Sin()
        val eps = 1e-10.toBigDecimal()
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            sin.calc(x.toBigDecimal(), eps)
        }
    }

}