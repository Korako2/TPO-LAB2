package tpo.lab2

import java.math.BigDecimal
import java.math.RoundingMode

class FunctionSystem {
    // desmos formulas:
    // x <= 0 : cot(x)
    // x > 0 : ((( \frac{((ln(x) ^ 2) - ln(x))}{log_5(x)} ) \cdot (ln(x) + (log_2(x) - log_{10}(x)))) \cdot log_3(x))
    private val cot = Cot()
    private val ln = Ln()
    private val log2 = Log(2)
    private val log3 = Log(3)
    private val log5 = Log(5)
    private val log10 = Log(10)

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return if (x <= BigDecimal.ZERO) {
            cot.calc(x, eps)
        } else {
            val lnX = ln.calc(x, eps)
            val log5X = log5.calc(x, eps)
            val log2X = log2.calc(x, eps)
            val log10X = log10.calc(x, eps)
            val lnX2 = lnX.pow(2)
            val lnX2MinusLnX = lnX2 - lnX
            val log2MinusLog10 = log2X - log10X
            val lnXPlusLog2MinusLog10 = lnX + log2MinusLog10
            val lnX2MinusLnXDivLog5X = lnX2MinusLnX / log5X
            val lnXPlusLog2MinusLog10MulLnX2MinusLnXDivLog5X = lnXPlusLog2MinusLog10 * lnX2MinusLnXDivLog5X
            lnXPlusLog2MinusLog10MulLnX2MinusLnXDivLog5X * log3.calc(x, eps)
        }.setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }
}