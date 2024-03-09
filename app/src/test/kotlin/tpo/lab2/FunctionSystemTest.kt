package tpo.lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.math.PI

class FunctionSystemTest {
    private val DEFAULT_PRECISION = 0.0001.toBigDecimal()

    @ParameterizedTest
    @MethodSource("getXAndYPairIfXGreaterThan0")
    fun `should calculate positive values`(x : Double, y : Double) {
        val system = FunctionSystem()
        val actual = system.calc(x.toBigDecimal(), DEFAULT_PRECISION)
        assertEquals(y, actual.toDouble(), 0.1)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun `should throw exception for x = 1 and x = 0`(x : Double) {
        val system = FunctionSystem()
        assertThrows(ArithmeticException::class.java) {
            system.calc(x.toBigDecimal(), DEFAULT_PRECISION)
        }
    }

    @ParameterizedTest
    @MethodSource("getXAndYPairIfXLessOfEquals0")
    fun `should calculate negative values`(x : Double, y : Double) {
        val system = FunctionSystem()
        val actual = system.calc(x.toBigDecimal(), DEFAULT_PRECISION)
        assertEquals(y, actual.toDouble(), 0.1)
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
                Arguments.of(-3*PI/2, 0.0),
                Arguments.of(-3, 7.015),
                Arguments.of(-1, -0.642),
                Arguments.of(-0.1, -9.967)
            )
        }
    }
}