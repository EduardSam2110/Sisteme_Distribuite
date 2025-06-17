package com.sd.laborator.services

import com.sd.laborator.interfaces.FileLoggerInterface
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.File

@Service
class FileLogger: FileLoggerInterface {
    override fun writeToFile(filename: String,text: String) {
        try {
            File(filename).appendText(text + '\n')
        } catch (e: IOException) {
            println("Eroare la scriere: ${e.message}")
        }
    }
}