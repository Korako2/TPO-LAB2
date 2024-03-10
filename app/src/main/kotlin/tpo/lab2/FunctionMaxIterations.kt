package tpo.lab2

import java.math.BigDecimal

interface FunctionMaxIterations {
    companion object {
        const val MAX_ITERATIONS = 1000000
    }
    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal
}