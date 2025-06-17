package com.sd.laborator.interfaces

interface FilterInterface<I, O> {
    fun process(input: I): O
}