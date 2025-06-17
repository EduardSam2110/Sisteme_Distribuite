package com.sd.laborator.interfaces

interface FileLoggerInterface {
    fun writeToFile(filename: String, text: String)
}