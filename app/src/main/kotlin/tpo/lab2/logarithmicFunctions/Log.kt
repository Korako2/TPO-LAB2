package tpo.lab2.logarithmicFunctions

import tpo.lab2.FunctionMaxIterations
import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.math.RoundingMode

class Log(private val base: Int): FunctionMaxIterations {
    private var ln = Ln()
    init {
        if (base == 1 || base <= 0) {
            throw IllegalArgumentException("Base of the logarithm cannot be 1.")
        }
    }
    override fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        if (x <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Function is not defined for x <= 0. x = $x")
        }
        return ln.calc(x, eps).divide(ln.calc(BigDecimal(base), eps), DECIMAL128.precision, RoundingMode.HALF_EVEN)
            .setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }

    fun setLn(ln: Ln) {
        this.ln = ln
    }
}