package com.sd.laborator

import io.micronaut.core.annotation.Introspected

@Introspected
data class CoinInput(
    val coin: String
)
