package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CosTest {
    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0])
    fun `test non-negative`(x: Double) {
        val cos = Cos()
        val eps = 1e-10.toBigDecimal()
        val expected = cos(x)
        val actual = cos.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [-0.5, -1.0, -1.5, -2.0, -2.5, -3.0, -3.5, -4.0, -4.5, -5.0])
    fun `test negative`(x: Double) {
        val cos = Cos()
        val eps = 1e-10.toBigDecimal()
        val expected = cos(x)
        val actual = cos.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [100.0, 1000.0, 10000.0, 100000*PI])
    fun `large argument numbers`(x : Double) {
        val cos = Cos()
        val eps = 1E-10.toBigDecimal()
        val expected = cos(x)
        val actual = cos.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @DisplayName("invalid argument")
    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `test invalid argument`(x: Double) {
        val cos = Cos()
        val eps = 1e-10.toBigDecimal()
        assertThrows(IllegalArgumentException::class.java) {
            cos.calc(x.toBigDecimal(), eps)
        }
    }

    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [0.0, 2* PI /3, -2* PI, -1001* PI /2])
    fun `frequency of the function`(x : Double) {
        val cos = Cos()
        val eps = 1E-10.toBigDecimal()
        val expected = cos(x)
        val actual = cos.calc(x.toBigDecimal() + (2*PI).toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = [0.0, 2* PI /3, -2* PI /6, -1001* PI /2])
    fun `parity of the function`(x : Double) {
        val cos = Cos()
        val eps = 1E-10.toBigDecimal()
        val expected = cos(x)
        val actual = cos.calc(-x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }
}