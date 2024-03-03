package tpo.lab2

import java.math.BigDecimal
import java.math.RoundingMode

class Cot {
    private val tan = Tan()

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        val tanValue = tan.calc(x, eps)
        if (tanValue.compareTo(BigDecimal.ZERO) == 0) {
            throw ArithmeticException("cot($x) is undefined")
        }
        return BigDecimal.ONE.divide(tanValue, eps.scale(), RoundingMode.HALF_EVEN)
    }
}