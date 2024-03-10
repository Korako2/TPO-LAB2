package tpo.lab2

import tpo.lab2.logarithmicFunctions.Ln
import tpo.lab2.logarithmicFunctions.Log
import tpo.lab2.trigonometricFunctions.Cos
import tpo.lab2.trigonometricFunctions.Cot
import tpo.lab2.trigonometricFunctions.Sin
import tpo.lab2.trigonometricFunctions.Tan
import tpo.lab2.utils.CSVWriter
import java.math.BigDecimal

private val directoryName = "csvFiles/"
fun main() {
    writeSinResult()
    writeCosResult()
    writeTanResult()
    writeCotResult()
    writeLnResult()
    writeLogResult(2)
    writeLogResult(3)
    writeLogResult(5)
    writeLogResult(10)

}

private fun writeSinResult() {
    val sin = Sin()
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/sin.csv", sin, (-2 * Math.PI).toBigDecimal(), (2 * Math.PI).toBigDecimal(), (Math.PI / 16).toBigDecimal(), BigDecimal("1E-10"))
}

private fun writeCosResult() {
    val cos = Cos()
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/cos.csv", cos, (-2 * Math.PI).toBigDecimal(), (2 * Math.PI).toBigDecimal(), (Math.PI / 16).toBigDecimal(), BigDecimal("1E-10"))
}

private fun writeTanResult() {
    val tan = Tan()
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/tan.csv", tan, (-Math.PI / 3).toBigDecimal(), (Math.PI / 3).toBigDecimal(), (Math.PI / 16).toBigDecimal(), BigDecimal("1E-10"))
}

private fun writeCotResult() {
    val cot = Cot()
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/cot.csv", cot, (-Math.PI / 3).toBigDecimal(), (Math.PI / 3).toBigDecimal(), (Math.PI / 16).toBigDecimal(), BigDecimal("1E-10"))
}

private fun writeLnResult() {
    val ln = Ln()
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/ln.csv", ln, BigDecimal("0.1"), BigDecimal("1.9"), BigDecimal("0.1"), BigDecimal("1E-10"))
}

private fun writeLogResult(n : Int) {
    val log = Log(n)
    val CSVWriter = CSVWriter()
    CSVWriter.writeToFile("$directoryName/log${n}.csv", log, BigDecimal("0.1"), BigDecimal("1.9"), BigDecimal("0.1"), BigDecimal("1E-3"))
}