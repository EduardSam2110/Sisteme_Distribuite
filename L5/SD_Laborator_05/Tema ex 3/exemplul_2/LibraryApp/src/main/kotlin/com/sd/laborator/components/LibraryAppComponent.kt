package com.sd.laborator.components

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.model.Book
import com.sd.laborator.model.Content
import com.sd.laborator.services.LibraryHTMLPrinter
import com.sd.laborator.services.LibraryJSONPrinter
import com.sd.laborator.services.LibraryRawPrinter
import com.sd.laborator.services.LibraryXMLPrinter
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception
import kotlin.random.Random

data class Comanda(val id: String, val timp: Int, val descriere: String)

@Component
class LibraryAppComponent {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var htmlPrinter: LibraryHTMLPrinter

    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent
    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    fun sendMessage(msg: Comanda) {
        val text = "${msg.id}###${msg.timp}${msg.descriere}"
        this.amqpTemplate.convertAndSend(connectionFactory.getExchange(),
                                         connectionFactory.getRoutingKey(),
                                         text)
    }

    @RabbitListener(queues = ["\${libraryapp.rabbitmq.queue}"])
    fun recieveMessage(msg: String) {
        // the result needs processing
        val processedMsg = (msg.split(",").map { it.toInt().toChar() }).joinToString(separator="")
        try {
            val (function, parameter) = processedMsg.split(":")
            val result: String? = when(function) {
                "print" -> customPrint(parameter)
                "find" -> customFind(parameter)
                "add" -> addBook(parameter)
                else -> null
            }
            if (result != null) sendMessage(comandaRandom())
        } catch (e: Exception) {
            println(e)
        }
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun comandaRandom(): Comanda {
        val id = getRandomString(5)
        val timp = Random.nextInt(1000,10000)
        return Comanda(id, timp, "Sunt comanda cu id = ${id} si am fost procesata in timp = ${timp}")
    }
}