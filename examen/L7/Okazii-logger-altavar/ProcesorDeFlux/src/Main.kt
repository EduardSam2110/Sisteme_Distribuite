import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader
import java.net.ServerSocket

class LoggerMicroservice {
    companion object {
        const val LOGGER_PORT = 1800
        const val LOG_FILE = "microservices.log"
    }

    fun run() {
        // Ensure the log file exists
        val logFile = File(LOG_FILE).apply { if (!exists()) createNewFile() }

        val server = ServerSocket(LOGGER_PORT)
        println("[LOGGER] LoggerMicroservice rulează pe portul: $LOGGER_PORT")
        println("[LOGGER] Scriu logurile în fișierul: $LOG_FILE")

        while (true) {
            val client = server.accept()
            println("[LOGGER] Conexiune de la ${client.inetAddress.hostAddress}")

            Thread {
                client.use { sock ->
                    val reader = BufferedReader(InputStreamReader(sock.getInputStream()))
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        val logLine = "[LOGGER] $line"
                        println(logLine)
                        // Append to file
                        FileWriter(logFile, true).use { fw ->
                            fw.appendln(logLine)
                        }
                    }
                }
            }.start()
        }
    }
}

fun main() {
    LoggerMicroservice().run()
}
