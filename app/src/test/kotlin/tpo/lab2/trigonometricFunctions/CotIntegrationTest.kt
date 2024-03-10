package tpo.lab2.trigonometricFunctions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import java.math.BigDecimal
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class CotIntegrationTest {

    val tanMock = Mockito.mock(Tan::class.java)
    private val eps = 1e-10.toBigDecimal()

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, -0.1, 0.1, 1.0, 10.0, 100.0])
    fun `test cot with mock tan`(x: Double) {
        val cot = Cot()
        cot.setTan(tanMock)
        Mockito.`when`(tanMock.calc(BigDecimal(x), eps)).thenReturn(tan(x).toBigDecimal())
        val expected = 1.0 / tan(x)
        val actual = cot.calc(BigDecimal(x), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-PI, 0.0, PI])
    fun `test cot(x) if x = PIk`(x: Double) {
        val cot = Cot()
        cot.setTan(tanMock)
        Mockito.`when`(tanMock.calc(BigDecimal(x), eps)).thenThrow(ArithmeticException::class.java)
        Assertions.assertThrows(ArithmeticException::class.java) {
            cot.calc(BigDecimal(x), eps)
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 1.0, 10.0, 100.0])
    fun `frequency of the function`(x : Double) {
        val cot = Cot()
        cot.setTan(tanMock)
        Mockito.`when`(tanMock.calc(eq(BigDecimal(x).plus(BigDecimal(PI))), any())).thenReturn(tan(x + PI).toBigDecimal())
        val expected = 1 / tan(x)
        val actual = cot.calc(BigDecimal(x).plus(BigDecimal(PI)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 1.0, 10.0, 100.0])
    fun `parity of the function`(x : Double) {
        val cot = Cot()
        cot.setTan(tanMock)
        Mockito.`when`(tanMock.calc(eq(BigDecimal(x).multiply(BigDecimal(-1))), any())).thenReturn(tan(-x).toBigDecimal())
        val expected = -1 / tan(x)
        val actual = cot.calc(BigDecimal(x).multiply(BigDecimal(-1)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }
}