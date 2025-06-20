package com.sd.laborator

import io.micronaut.core.annotation.Introspected

@Introspected
data class Event(
    var currentState: String,
    var coin: String
)
