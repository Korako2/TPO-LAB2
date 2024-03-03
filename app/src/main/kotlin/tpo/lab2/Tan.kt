package tpo.lab2

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class Tan {
    private val sin = Sin()
    private val cos = Cos()

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        val newEps = eps.divide(BigDecimal.TEN, eps.scale() + 1, RoundingMode.HALF_EVEN)
        val cosValue = cos.calc(x, newEps)
        if (cosValue.compareTo(BigDecimal.ZERO) == 0) {
            throw ArithmeticException("tan($x) is undefined")
        }
        return sin.calc(x, newEps).divide(cosValue, MathContext.DECIMAL128.precision, RoundingMode.HALF_EVEN)
            .setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }
}