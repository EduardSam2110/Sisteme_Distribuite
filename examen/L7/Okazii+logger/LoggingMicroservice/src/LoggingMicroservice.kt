import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class LoggingMicroservice {
    companion object {
        const val LOGGING_PORT = 1800
    }

    private val logFile = File("operation_logs.txt")

    init {
        val server = ServerSocket(LOGGING_PORT)
        println("LoggingMicroservice rulează pe portul $LOGGING_PORT")

        while (true) {
            val client = server.accept()
            Thread {
                handleLogging(client)
            }.start()
        }
    }

    private fun handleLogging(client: Socket) {
        client.getInputStream().bufferedReader().use { reader ->
            val logLine = reader.readLine()
            if (logLine != null) {
                logToFile(logLine)
                println("[LOGGING] $logLine")
            }
        }
        client.close()
    }

    private fun logToFile(log: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        val fullLog = "[$timestamp] $log"
        PrintWriter(FileWriter(logFile, true)).use { // <-- aici e cheia: append=true
            it.println(fullLog)
        }
    }
}

fun main() {
    LoggingMicroservice()
}
