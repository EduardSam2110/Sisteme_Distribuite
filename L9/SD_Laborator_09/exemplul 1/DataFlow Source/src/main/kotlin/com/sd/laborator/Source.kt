package com.sd.laborator

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.util.*

import org.springframework.messaging.converter.MessageConverter
import kotlinx.serialization.json.Json
import org.springframework.messaging.MessageHeaders
import org.springframework.util.MimeType
import org.springframework.util.MimeTypeUtils

@EnableBinding(Source::class)
@SpringBootApplication
class SpringDataFlowTimeSourceApplication {
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = [Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")])
    fun commandMessageSource(): () -> Message<String> {
        val message = buildJsonObject {
            put("command", "fortune | cowsay | figlet")
            put("output", "")
        }.toString()
        return { MessageBuilder.withPayload(message).build() }
    }

    /*

    fun timeMessageSource(): () -> Message<Long> {
        return { MessageBuilder.withPayload(Date().time).build() }
    }

     */
}

fun main(args: Array<String>) {
    runApplication<SpringDataFlowTimeSourceApplication>(*args)
}