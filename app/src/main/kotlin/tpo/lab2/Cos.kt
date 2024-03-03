package tpo.lab2

import java.math.BigDecimal

class Cos {
    private val sin = Sin()

    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return sin.calc(x + BigDecimal(Math.PI / 2), eps)
    }
}