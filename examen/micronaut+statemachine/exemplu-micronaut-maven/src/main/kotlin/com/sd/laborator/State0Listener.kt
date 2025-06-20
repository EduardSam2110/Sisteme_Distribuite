package com.sd.laborator

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Inject

@KafkaListener(groupId = "state-0-consumer")
class State0Listener {

    @Inject
    lateinit var state0Function: State0Function

    @Topic("state-0")
    fun receive(event: Event) {
        state0Function.apply(event)
    }
}
