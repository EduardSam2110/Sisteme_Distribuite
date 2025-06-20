package com.sd.laborator

import com.sd.laborator.KafkaProducerClient
import com.sd.laborator.CoinInput
import com.sd.laborator.Event
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/coin")
class InputController(
    private val kafkaProducer: KafkaProducerClient
) {

    @Post("/")
    fun receiveCoin(@Body input: CoinInput): HttpResponse<String> {
        val event = Event(currentState = "0", coin = input.coin)
        kafkaProducer.sendToState0(event)
        return HttpResponse.ok("Coin received: ${input.coin}")
    }

}
