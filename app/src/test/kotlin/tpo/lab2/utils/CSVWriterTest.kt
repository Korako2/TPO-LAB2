package tpo.lab2.utils

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import tpo.lab2.FunctionMaxIterations
import java.io.File
import java.math.BigDecimal

class CSVWriterTest {

    private val testDirectoryName = "test/kotlin/tpo/lab2/csv"
    private val testFileName = "$testDirectoryName/test.csv"

    @BeforeEach
    fun setUp() {
        val directory = File(testDirectoryName)
        directory.mkdirs()
    }

    @AfterEach
    fun tearDown() {
        val directory = File(testDirectoryName)
        directory.deleteRecursively()
    }

    @Test
    fun `test write to file`() {
        val writer = CSVWriter()
        writer.writeToFile(testFileName, object : FunctionMaxIterations {
            override fun calc(x: BigDecimal, eps: BigDecimal): BigDecimal {
                return x
            }
        }, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ONE)
        val file = java.io.File("test/kotlin/tpo/lab2/csv/test.csv")
        val expected = "0 0\n1 1\n2 2\n3 3\n4 4\n5 5\n6 6\n7 7\n8 8\n9 9\n10 10\n"
        assertEquals(expected, file.readText())
    }
}