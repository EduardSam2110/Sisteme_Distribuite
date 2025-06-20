package com.sd.laborator


import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaProducerClient {

    @Topic("state-0")
    fun sendToState0(event: Event)
    @Topic("state-5")
    fun sendToState5(event: Event)

    @Topic("state-10")
    fun sendToState10(event: Event)

    @Topic("state-15")
    fun sendToState15(event: Event)

    @Topic("state-final")
    fun sendToFinalState(event: Event)
}
