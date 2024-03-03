package tpo.lab2

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.math.RoundingMode

// TODO: Вероятно, нужно ввести ограничение по кол-ву итераций, когда он не может достичь нужной точности
class Ln {
    // https://en.wikipedia.org/wiki/Natural_logarithm#Series
    // ln(x) = sum(n=1..inf) (-1)^(n+1) * x^n / n
    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        // or compareTo?
        if (x <= ZERO) {
            throw IllegalArgumentException("x must be positive")
        }
        if (x == ONE) {
            return ZERO
        }

        // почему у РедГрая какая-то усложненная реализация? Вот простая:
        var result = ZERO
        var n = ONE
        var term = x - ONE
        var sign = -ONE
        var prevX = x - ONE
        // начинаем со второго члена ряда
        while (term.abs() > BigDecimal("1E-${eps.scale()+1}")) {
            result += term
            n += ONE
            prevX *= (x - ONE)
            term = (prevX * sign).divide(n, eps.scale() * 2, RoundingMode.HALF_EVEN)
            sign = -sign
        }
        return result.setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }
}