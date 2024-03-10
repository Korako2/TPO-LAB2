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

class TanIntegrationTest {

    val sinMock = Mockito.mock(Sin::class.java)
    val cosMock = Mockito.mock(Cos::class.java)
    private val eps = 1e-10.toBigDecimal()
    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 0.0, 1.0, 10.0, 100.0])
    fun `test tan with mock sin and cos`(x: Double) {
        val tan = Tan()
        tan.setSin(sinMock)
        tan.setCos(cosMock)
        Mockito.`when`(sinMock.calc(eq(BigDecimal(x)), any())).thenReturn(sin(x).toBigDecimal())
        Mockito.`when`(cosMock.calc(eq(BigDecimal(x)), any())).thenReturn(cos(x).toBigDecimal())
        val expected = tan(x)
        val actual = tan.calc(BigDecimal(x), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [PI /2, -PI /2, 75* PI /2])
    fun `test tan(PI div 2)`(x: Double) {
        val tan = Tan()
        val eps = 1e-3.toBigDecimal()
        Mockito.`when`(sinMock.calc(eq(x.toBigDecimal()), any())).thenReturn(sin(x).toBigDecimal())
        Mockito.`when`(cosMock.calc(eq(x.toBigDecimal()), any())).thenReturn(cos(x).toBigDecimal())
        Assertions.assertThrows(ArithmeticException::class.java) {
            tan.calc(x.toBigDecimal(), eps)
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 0.0, 1.0, 10.0, 100.0])
    fun `test tan with mock sin`(x: Double) {
        val tan = Tan()
        tan.setSin(sinMock)
        Mockito.`when`(sinMock.calc(eq(BigDecimal(x)), any())).thenReturn(sin(x).toBigDecimal())
        val expected = tan(x)
        val actual = tan.calc(BigDecimal(x), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 0.0, 1.0, 10.0, 100.0])
    fun `test tan with mock cos`(x: Double) {
        val tan = Tan()
        Mockito.`when`(cosMock.calc(eq(x.toBigDecimal()), any())).thenReturn(cos(x).toBigDecimal())
        val expected = tan(x)
        val actual = tan.calc(x.toBigDecimal(), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 0.0, 1.0, 10.0, 100.0])
    fun `frequency of the function`(x : Double) {
        val tan = Tan()
        tan.setSin(sinMock)
        tan.setCos(cosMock)
        Mockito.`when`(sinMock.calc(eq(BigDecimal(x).plus(BigDecimal(PI))), any())).thenReturn(sin(x + PI).toBigDecimal())
        Mockito.`when`(cosMock.calc(eq(BigDecimal(x).plus(BigDecimal(PI))), any())).thenReturn(cos(x + PI).toBigDecimal())
        val expected = tan(x)
        val actual = tan.calc(BigDecimal(x).plus(BigDecimal(PI)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(doubles = [-100.0, -10.0, -1.0, 0.0, 1.0, 10.0, 100.0])
    fun `parity of the function`(x : Double) {
        val tan = Tan()
        tan.setSin(sinMock)
        tan.setCos(cosMock)
        Mockito.`when`(sinMock.calc(eq(BigDecimal(x).multiply(BigDecimal(-1))), any())).thenReturn(sin(-x).toBigDecimal())
        Mockito.`when`(cosMock.calc(eq(BigDecimal(x).multiply(BigDecimal(-1))), any())).thenReturn(cos(-x).toBigDecimal())
        val expected = -tan(x)
        val actual = tan.calc(BigDecimal(x).multiply(BigDecimal(-1)), eps).toDouble()
        assertEquals(expected, actual, eps.toDouble())
    }

}