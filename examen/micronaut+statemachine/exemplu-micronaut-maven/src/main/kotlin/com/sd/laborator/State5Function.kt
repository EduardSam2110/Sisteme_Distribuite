package com.sd.laborator

import io.micronaut.function.FunctionBean
import jakarta.inject.Inject
import java.util.function.Function

@FunctionBean("state-5")
class State5Function : Function<Event, String> {

    @Inject
    lateinit var kafkaProducerClient: KafkaProducerClient

    override fun apply(input: Event): String {
        return when (input.coin) {
            "Nickel" -> {
                kafkaProducerClient.sendToState10(Event("10", "Nickel"))
                "Sent to state 10"
            }
            "Dime" -> {
                kafkaProducerClient.sendToState15(Event("15", "Dime"))
                "Sent to state 15"
            }
            else -> {
                "Invalid coin"
            }
        }
    }
}
