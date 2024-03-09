package tpo.lab2.trigonometricFunctions

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.PI

class Cot {
    private val tan = Tan()

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        if (isPointsOfVerticalDiameter(x)) return 0.0.toBigDecimal()
        val tanValue = tan.calc(x, eps)
        if (tanValue.compareTo(BigDecimal.ZERO) == 0) {
            throw ArithmeticException("cot($x) is undefined")
        }
        return BigDecimal.ONE.divide(tanValue, eps.scale(), RoundingMode.HALF_EVEN)
    }

    private fun isPointsOfVerticalDiameter(x: BigDecimal): Boolean {
        return (PI/2).toBigDecimal().subtract(x.abs().remainder(PI.toBigDecimal().multiply(2.toBigDecimal()))).abs()<= (1E-10).toBigDecimal() ||
                (3*PI/2).toBigDecimal().subtract(x.abs().remainder(PI.toBigDecimal().multiply(2.toBigDecimal()))).abs() <= (1E-10).toBigDecimal()
    }
}