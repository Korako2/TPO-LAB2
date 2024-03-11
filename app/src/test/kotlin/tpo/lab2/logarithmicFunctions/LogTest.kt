package tpo.lab2.logarithmicFunctions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.math.log

class LogTest {

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, 0) throws Exception`(n: Int) {
        val log = Log(n)
        val x = 0
        val eps = 0
        assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, 1) is 0`(n : Int) {
        val log = Log(n)
        val x = 1
        val eps = 1
        assertEquals(0.toBigDecimal(), log.calc(x.toBigDecimal(), eps.toBigDecimal()))
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.5, 1.0, 1.5, -0.5])
    fun `log(1, n) throws Exception`(x: Double) {
        val eps = 1
        assertThrows(IllegalArgumentException::class.java) { Log(1).calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }

    @ParameterizedTest
    @MethodSource("getNonNegativeArgumentsAndBase")
    fun `log(n, non-negative)`(n: Int, x: Double) {
        val log = Log(n)
        val eps = 1e-3.toBigDecimal()
        val expected = log(x, n.toDouble())
        val actual = log.calc(x.toBigDecimal(), eps)
        assertEquals(expected, actual.toDouble(), eps.toDouble())
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `log(n, negative) throws Exception`(n : Int) {
        val log = Log(n)
        val x = -1
        val eps = 1
        assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }


    @ParameterizedTest
    @MethodSource("getInvalidArgumentsAndBase")
    fun `log(n, invalid_argument) throws Exception`(n : Int, x : Double) {
        val log = Log(n)
        val eps = Double.NaN
        assertThrows(IllegalArgumentException::class.java) { log.calc(x.toBigDecimal(), eps.toBigDecimal()) }
    }

    companion object{
        @JvmStatic
        private fun getInvalidArgumentsAndBase(): Stream<Arguments> {
            return generateAllCombinationsOfArgumentsAndBase()
        }

        private fun generateAllCombinationsOfArgumentsAndBase(): Stream<Arguments> {
            val invalidValues = listOf(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)
            val baseValues = (2..10).toList()
            val combinations = mutableListOf<Arguments>()

            for (invalidValue in invalidValues) {
                for (base in baseValues) {
                    combinations.add(Arguments.of(base, invalidValue))
                }
            }

            return combinations.stream()
        }

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