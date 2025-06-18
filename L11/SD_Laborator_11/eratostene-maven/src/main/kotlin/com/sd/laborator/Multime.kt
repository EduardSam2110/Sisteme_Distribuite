package com.sd.laborator

import io.micronaut.core.annotation.Introspected
import kotlin.random.Random

@Introspected
data class Multime(val valori: Set<Int>) {
    constructor(size: Int) : this(
        (1..size).map { Random.nextInt(0, 1000) }.toSet()
    )
}