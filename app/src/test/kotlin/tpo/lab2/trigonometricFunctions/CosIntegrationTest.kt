package tpo.lab2.trigonometricFunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.math.BigDecimal
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CosIntegrationTest {
    private val eps = 1e-10.toBigDecimal()
    private val sinMock = mock(Sin::class.java)
    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.5, 1.0, 10.0, 10000.0])
    fun `test non-negative`(x: Double) {
        val cos = Cos()
        cos.setSin(sinMock)
        Mockito.`when`(sinMock.calc(BigDecimal(x).plus(BigDecimal(PI/2)), eps)).thenReturn(sin(x + PI/2).toBigDecimal())
        val expected = sinMock.calc(BigDecimal(x).plus(BigDecimal(PI/2)), eps)
        val actual = cos.calc(BigDecimal(x), eps)
        assertEquals(expected.toDouble(), actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-0.5, -1.0, -10.0, -10000.0])
    fun `test negative`(x: Double) {
        val cos = Cos()
        cos.setSin(sinMock)
        Mockito.`when`(sinMock.calc(BigDecimal(x).plus(BigDecimal(PI/2)), eps)).thenReturn(sin(x + PI/2).toBigDecimal())
        val expected = sinMock.calc(BigDecimal(x).plus(BigDecimal(PI/2)), eps)
        val actual = cos.calc(BigDecimal(x), eps)
        assertEquals(expected.toDouble(), actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 2* PI /3, -2* PI, -1001* PI /2])
    fun `frequency of the function`(x : Double) {
        val cos = Cos()
        cos.setSin(sinMock)
        Mockito.`when`(sinMock.calc(BigDecimal(x).plus(BigDecimal(PI/2).plus(BigDecimal(2 * PI))), eps)).thenReturn(sin(x + PI/2 + 2 * PI).toBigDecimal())
        val expected = cos(x)
        val actual = cos.calc(BigDecimal(x).plus(BigDecimal(2*PI)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 2* PI /3, -2* PI /6, -1001* PI /2])
    fun `parity of the function`(x : Double) {
        val cos = Cos()
        cos.setSin(sinMock)
        Mockito.`when`(sinMock.calc(BigDecimal(x).multiply(BigDecimal(-1.0)).plus(BigDecimal(PI/2)), eps)).thenReturn(sin(-x + PI/2).toBigDecimal())
        val expected = cos(x)
        val actual = cos.calc(BigDecimal(x).multiply(BigDecimal(-1.0)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }
}