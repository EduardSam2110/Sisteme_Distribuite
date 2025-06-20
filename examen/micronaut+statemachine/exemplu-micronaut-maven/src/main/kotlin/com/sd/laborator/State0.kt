package com.sd.laborator

import io.micronaut.function.FunctionBean
import jakarta.inject.Inject
import java.util.function.Function

@FunctionBean("state-0")
class State0Function : Function<Event, String> {

    @Inject
    lateinit var kafkaProducerClient: KafkaProducerClient

    override fun apply(input: Event): String {
        return when (input.coin) {
            "Nickel" -> {
                kafkaProducerClient.sendToState5(Event("5", "Nickel"))
                "Sent to state 5"
            }
            "Dime" -> {
                kafkaProducerClient.sendToState10(Event("10", "Dime"))
                "Sent to state 10"
            }
            else -> {
                System.err.println("Invalid coin: ${input.coin}")
                "Invalid coin"
            }
        }
    }
}