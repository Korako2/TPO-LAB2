package tpo.lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
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

    @DisplayName("cot(PI) and cot(0) is undefined")
    @ParameterizedTest(name = "cot({0})")
    @ValueSource(doubles = [0.0, Math.PI])
    fun `test cot(PI) and cot(0)`(x: Double) {
        val cot = Cot()
        val eps = 1e-3.toBigDecimal()
        assertThrows(ArithmeticException::class.java) {
            cot.calc(x.toBigDecimal(), eps)
        }
    }

}