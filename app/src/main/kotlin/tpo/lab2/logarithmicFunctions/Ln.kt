package tpo.lab2.logarithmicFunctions

import java.math.BigDecimal
import java.math.BigDecimal.*
import java.math.MathContext
import java.math.RoundingMode

// TODO: Вероятно, нужно ввести ограничение по кол-ву итераций, когда он не может достичь нужной точности
class Ln {
    // https://en.wikipedia.org/wiki/Natural_logarithm#Series
    fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
        // or compareTo?
        if (x <= ZERO) {
            throw IllegalArgumentException("x must be positive")
        }
        if (x == ONE) {
            return ZERO
        }

        var result = ZERO
        var n = ONE
        // начинаем со второго члена ряда
        if ((x - ONE).abs() <= ONE) {
            // ln(x) = sum(n=1..inf) (-1)^(n+1) * x^n / n
            var term = x - ONE
            var prevResult = result
            var xMinusOnePowered = x - ONE
            var sign = -ONE
            while ((result - prevResult).abs() > BigDecimal("1E-${eps.scale() + 1}") || n == ONE) {
                prevResult = result
                result += term
                n += ONE
                xMinusOnePowered *= (x - ONE)
                term = (xMinusOnePowered * sign).divide(n, MathContext.DECIMAL128.precision, RoundingMode.HALF_UP)
                sign = -sign
            }
            return result;
        } else {
            var prevXMinusOnePowered = x - ONE
            var prevXPowered = x
            var prevResult = result
            while ((result - prevResult).abs() > BigDecimal("1E-${eps.scale() + 1}") || n == ONE) {
                val term = prevXMinusOnePowered.divide(n, MathContext.DECIMAL128.precision, RoundingMode.HALF_UP)
                    .divide(prevXPowered, MathContext.DECIMAL128.precision, RoundingMode.HALF_UP)
                prevXMinusOnePowered *= x - ONE
                prevXPowered *= x
                n += ONE
                prevResult = result
                result += term
            }
        }
        return result.setScale(eps.scale(), RoundingMode.HALF_EVEN)
    }
}