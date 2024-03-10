package tpo.lab2.trigonometricFunctions

import tpo.lab2.FunctionMaxIterations
import java.math.BigDecimal
import kotlin.math.PI

class Cos: FunctionMaxIterations {
    var sin = Sin()

    override fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return sin.calc(x.plus(BigDecimal(PI / 2)), eps)
    }
}