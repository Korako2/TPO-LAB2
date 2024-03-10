package tpo.lab2.utils

class CSVWriter {

    fun writeToFile(fileName: String, data: List<List<String>>) {
        val file = java.io.File(fileName)
        file.writeText("")
        data.forEach {
            file.appendText(it.joinToString(",") + "\n")
        }
    }
}