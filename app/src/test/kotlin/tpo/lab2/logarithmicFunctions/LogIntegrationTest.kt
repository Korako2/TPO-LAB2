package tpo.lab2.logarithmicFunctions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import java.math.BigDecimal
import java.util.stream.Stream
import kotlin.math.ln
import kotlin.math.log

class LogIntegrationTest {
    private val mockLn = Mockito.mock(Ln::class.java)
    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, 0) throws Exception`(n: Int) {
        val log = Log(n)
        log.setLn(mockLn)
        Mockito.`when`(mockLn.calc(0.toBigDecimal(), 0.toBigDecimal())).thenThrow(IllegalArgumentException::class.java)
        val x = 0
        val eps = 0
        Assertions.assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, 1) is 0`(n : Int) {
        val log = Log(n)
        log.setLn(mockLn)
        val eps = 1
        val x = 1
        Mockito.`when`(mockLn.calc(BigDecimal(x), BigDecimal(eps))).thenReturn(BigDecimal(0))
        Mockito.`when`(mockLn.calc(BigDecimal(n), BigDecimal(eps))).thenReturn(ln(n.toDouble()).toBigDecimal())
        Assertions.assertEquals(BigDecimal(0), log.calc(BigDecimal(x), BigDecimal(eps)))
    }

    @ParameterizedTest
    @MethodSource("getNonNegativeArgumentsAndBase")
    fun `log(n, non-negative)`(n: Int, x: Double) {
        val log = Log(n)
        log.setLn(mockLn)
        val eps = 1e-3.toBigDecimal()
        Mockito.`when`(mockLn.calc(eq(BigDecimal(x)), any())).thenReturn(BigDecimal(ln(x)))
        Mockito.`when`(mockLn.calc(eq(BigDecimal(n)), any())).thenReturn(BigDecimal(ln(n.toDouble())))
        val expected = log(x, n.toDouble())
        val actual = log.calc(BigDecimal(x), eps)
        Assertions.assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, negative) throws Exception`(n : Int) {
        val log = Log(n)
        log.setLn(mockLn)
        Mockito.`when`(mockLn.calc(eq((-1).toBigDecimal()), any())).thenThrow(IllegalArgumentException::class.java)
        val x = -1
        val eps = 1
        Assertions.assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }


    companion object{

        @JvmStatic
        private fun getNonNegativeArgumentsAndBase(): Stream<Arguments> {
            return generateAllNonNegativeCombinationOfArgumentsAndBase()
        }

        private fun generateAllNonNegativeCombinationOfArgumentsAndBase(): Stream<Arguments> {
            val nonNegativeValues = (1..10).toList().map { it.toDouble() * 0.5 }
            val baseValues = listOf(2, 3, 5, 10)
            val combinations = mutableListOf<Arguments>()

            for (nonNegativeValue in nonNegativeValues) {
                for (base in baseValues) {
                    combinations.add(Arguments.of(base, nonNegativeValue))
                }
            }

            return combinations.stream()
        }
    }
}