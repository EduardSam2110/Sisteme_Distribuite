package com.sd.laborator

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Inject

@KafkaListener(groupId = "state-5-consumer")
class State5Listener {

    @Inject
    lateinit var state5Function: State5Function

    @Topic("state-0")
    fun receive(event: Event) {
        state5Function.apply(event)
    }
}
