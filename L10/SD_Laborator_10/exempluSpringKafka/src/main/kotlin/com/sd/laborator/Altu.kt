package com.sd.laborator

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Altu {
    @KafkaListener(topics = ["topic_exemplu_kotlin"], groupId = "exemplu-consumer-kotlin2")
    fun processMessage(message: String) {
        println("Altu: $message")
    }
}