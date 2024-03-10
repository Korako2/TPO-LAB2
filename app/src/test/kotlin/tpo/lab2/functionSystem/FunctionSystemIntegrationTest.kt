package tpo.lab2.functionSystem

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import tpo.lab2.logarithmicFunctions.Ln
import tpo.lab2.logarithmicFunctions.Log
import tpo.lab2.trigonometricFunctions.Cot
import java.math.BigDecimal
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.tan

class FunctionSystemIntegrationTest {
    private val DEFAULT_PRECISION = 0.0001.toBigDecimal()
    private val mockLn = Mockito.mock(Ln::class.java)
    private val mockLog2 = Mockito.mock(Log::class.java)
    private val mockLog3 = Mockito.mock(Log::class.java)
    private val mockLog5 = Mockito.mock(Log::class.java)
    private val mockLog10 = Mockito.mock(Log::class.java)
    private val mockCot = Mockito.mock(Cot::class.java)
    private val bigDecimal = Mockito.mock(BigDecimal::class.java)

    @ParameterizedTest
    @MethodSource("getXAndYPairIfXGreaterThan0")
    fun `should calculate positive values with all mocks`(x : Double, y : Double) {
        val system = FunctionSystem()
        system.setLog2(mockLog2)
        system.setLog3(mockLog3)
        system.setLog5(mockLog5)
        system.setLog10(mockLog10)
        system.setLn(mockLn)
        Mockito.`when`(mockLog2.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(log(x, 2.0)))
        Mockito.`when`(mockLog3.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(log(x, 3.0)))
        Mockito.`when`(mockLog5.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(log(x, 5.0)))
        Mockito.`when`(mockLog10.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(log(x, 10.0)))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(ln(x)))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(2)), any())).thenReturn(BigDecimal(ln(2.toDouble())))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(3)), any())).thenReturn(BigDecimal(ln(3.toDouble())))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(5)), any())).thenReturn(BigDecimal(ln(5.toDouble())))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(10)), any())).thenReturn(BigDecimal(ln(10.toDouble())))
        val actual = system.calc(BigDecimal(x), DEFAULT_PRECISION)
        Assertions.assertEquals(y, actual.toDouble(), 0.1)
    }

    @ParameterizedTest
    @MethodSource("getXAndYPairIfXLessOfEquals0")
    fun `should calculate negative values with cot mock`(x : Double, y : Double) {
        val system = FunctionSystem()
        system.setCot(mockCot)
        Mockito.`when`(mockCot.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(1/tan(x)))
        val actual = system.calc(BigDecimal(x), DEFAULT_PRECISION)
        Assertions.assertEquals(y, actual.toDouble(), 0.1)
    }

    companion object {
        @JvmStatic
        private fun getXAndYPairIfXGreaterThan0(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0.1, -51.52),
                Arguments.of(0.2, -19.89),
                Arguments.of(3.709, 1.57),
                Arguments.of(5, 4.65),
                Arguments.of(   10, 20.32),
                Arguments.of(100, 224.956)
            )
        }

        @JvmStatic
        private fun getXAndYPairIfXLessOfEquals0(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(-5, 0.296),
                Arguments.of(-3* PI /2, 0.0),
                Arguments.of(-3, 7.015),
                Arguments.of(-1, -0.642),
                Arguments.of(-0.1, -9.967)
            )
        }
    }

}