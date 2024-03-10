package tpo.lab2.trigonometricFunctions

import tpo.lab2.FunctionMaxIterations
import java.math.BigDecimal

class Cos: FunctionMaxIterations {
    private val sin = Sin()

    override fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return sin.calc(x + BigDecimal(Math.PI / 2), eps)
    }
}