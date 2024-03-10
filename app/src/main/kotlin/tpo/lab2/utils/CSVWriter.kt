package tpo.lab2.utils

import tpo.lab2.FunctionMaxIterations
import java.math.BigDecimal

class CSVWriter {

    fun writeToFile(fileName: String, function: FunctionMaxIterations, start: BigDecimal, end: BigDecimal, step: BigDecimal, eps: BigDecimal) {
        val file = java.io.File(fileName)
        file.writeText("x, y\n")
        var x = start
        while (x <= end) {
            file.appendText("${x.toPlainString()}, ${function.calc(x, eps).toPlainString()}\n")
            x += step
        }
    }
}