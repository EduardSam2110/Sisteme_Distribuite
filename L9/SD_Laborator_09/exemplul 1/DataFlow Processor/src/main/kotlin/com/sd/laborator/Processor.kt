package com.sd.laborator

import kotlinx.serialization.json.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.integration.annotation.Transformer
import java.text.DateFormat
import java.text.SimpleDateFormat

@EnableBinding(Processor::class)
@SpringBootApplication
class SpringDataFlowTimeProcessorApplication {
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    fun transform(input: String): String {
        val json = Json.parseToJsonElement(input).jsonObject
        var command = json["command"]?.jsonPrimitive?.content ?: ""
        val prevOutput = json["output"]?.jsonPrimitive?.content ?: ""

        var cmd: String
        if (command.contains(" | ")) {
            cmd = command.substringBefore(" | ")
            command = command.substringAfter(" | ")
        } else {
            cmd = command
            command = ""
        }

        var output = try {
            val process = Runtime.getRuntime().exec(cmd)
            if (prevOutput.isNotEmpty()) {
                process.outputStream.bufferedWriter().use {
                    it.write(prevOutput)
                    it.flush()
                }
            }
            process.inputStream.bufferedReader().readText()
        } catch (e: Exception) {
            "Eroare: ${e.message}"
        }

        val response = buildJsonObject {
            put("command", command)
            put("output", output)
        }

        return response.toString() // întoarcem tot ca string JSON
    }


    /*
    fun transform(timestamp: Long?): Any? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        return dateFormat.format(timestamp)
    }
     */
}

fun main(args: Array<String>) {
    runApplication<SpringDataFlowTimeProcessorApplication>(*args)
}