package com.sd.laborator.components

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.model.Book
import com.sd.laborator.services.LibraryHTMLPrinter
import com.sd.laborator.services.LibraryJSONPrinter
import com.sd.laborator.services.LibraryRawPrinter
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class LibraryAppComponent {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var htmlPrinter: LibraryHTMLPrinter

    @Autowired
    private lateinit var jsonPrinter: LibraryJSONPrinter

    @Autowired
    private lateinit var rawPrinter: LibraryRawPrinter


    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent
    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    fun sendMessage(msg: String) {
        this.amqpTemplate.convertAndSend(connectionFactory.getExchange(),
                                         connectionFactory.getRoutingKey(),
                                         msg)
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
                else -> null
            }
            if (result != null) sendMessage(result)
        } catch (e: Exception) {
            println(e)
        }
    }

    fun customPrint(format: String): String {
        return when(format) {
            "html" -> htmlPrinter.printHTML(libraryDAO.getBooks())
            "json" -> jsonPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> rawPrinter.printRaw(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    fun customPrint(format: String, books: Set<Book>): String {
        return when(format) {
            "html" -> htmlPrinter.printHTML(books)
            "json" -> jsonPrinter.printJSON(books)
            "raw" -> rawPrinter.printRaw(books)
            else -> "Not implemented"
        }
    }

    fun customFind(searchParameter: String): String {
        val (field, value, format) = searchParameter.split("=")
        print(field + " " + value + " " + format)
        return when(field) {
            "author" -> customPrint(format, this.libraryDAO.findAllByAuthor(value))
            "title" -> customPrint(format, this.libraryDAO.findAllByTitle(value))
            "publisher" -> customPrint(format, this.libraryDAO.findAllByPublisher(value))
            else -> "Not a valid field"
        }
    }

    fun addBook(book: Book): Boolean {
        return try {
            this.libraryDAO.addBook(book)
            true
        } catch (e: Exception) {
            false
        }
    }

}