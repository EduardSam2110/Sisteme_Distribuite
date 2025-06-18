package com.sd.laborator

import io.micronaut.core.annotation.Introspected

@Introspected
class EratosteneRequest {
    private lateinit var number: Integer
    private var operation: String = "primes" // Default operation

    fun getNumber(): Int {
        return number.toInt()
    }

    fun getOperation(): String {
        return operation
    }

    fun setOperation(operation: String) {
        this.operation = operation
    }
}