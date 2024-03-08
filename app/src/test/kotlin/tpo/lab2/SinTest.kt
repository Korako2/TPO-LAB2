package tpo.lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.sin

class SinTest {
    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
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
    @DisplayName("Large argument numbers")
    @ValueSource(doubles = [100.0, 1000.0, 10000.0])
    fun `large argument numbers`(x : Double) {
        val sin = Sin()
        val eps = 1E-10.toBigDecimal()
        val expected = sin(x)
        val actual = sin.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }
}