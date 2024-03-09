package tpo.lab2.trigonometricFunctions;

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.math.MathContext
import java.math.RoundingMode

class Sin {
    // divide x to get it in range (-pi, pi)
    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        val pi = BigDecimal(Math.PI)
        val two = 2.toBigDecimal()
        val x = x.remainder(pi.multiply(two))

        var result = ZERO
        var numeratorCoeff = x
        var denominatorCoeff = ONE
        var term = numeratorCoeff / denominatorCoeff
        var sign = -ONE
        var step = 1.toBigDecimal()

        while (term.abs() > BigDecimal("1E-${eps.scale()+1}")) {
            result += term
            numeratorCoeff *= x * x
            denominatorCoeff *= (step + ONE) * (step + 2.toBigDecimal())
            term = numeratorCoeff.divide(denominatorCoeff, MathContext.DECIMAL128.precision, RoundingMode.HALF_EVEN) * sign
            sign = -sign
            step += 2.toBigDecimal()
        }

        return result.setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }
}
