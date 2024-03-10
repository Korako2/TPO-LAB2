package tpo.lab2.functionSystem

import tpo.lab2.logarithmicFunctions.Ln
import tpo.lab2.logarithmicFunctions.Log
import tpo.lab2.trigonometricFunctions.Cot
import java.math.BigDecimal
import java.math.RoundingMode

class FunctionSystem {
    // desmos formulas:
    // x <= 0 : cot(x)
    // x > 0 : ((( \frac{((ln(x) ^ 2) - ln(x))}{log_5(x)} ) \cdot (ln(x) + (log_2(x) - log_{10}(x)))) \cdot log_3(x))
    private var cot = Cot()
    private var ln = Ln()
    private var log2 = Log(2)
    private var log3 = Log(3)
    private var log5 = Log(5)
    private var log10 = Log(10)

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return if (x <= BigDecimal.ZERO) {
            cot.calc(x, eps)
        } else {
            if (x == BigDecimal.ONE) {
                throw ArithmeticException("x cannot be 1")
            }
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
        }.setScale(eps.scale(), RoundingMode.HALF_UP)
    }

    fun setCot(cot: Cot) {
        this.cot = cot
    }

    fun setLn(ln: Ln) {
        this.ln = ln
    }

    fun setLog2(log2: Log) {
        this.log2 = log2
    }

    fun setLog3(log3: Log) {
        this.log3 = log3
    }

    fun setLog5(log5: Log) {
        this.log5 = log5
    }

    fun setLog10(log10: Log) {
        this.log10 = log10
    }
}