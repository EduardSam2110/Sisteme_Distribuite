package com.sd.laborator.services

import com.sd.laborator.interfaces.HTMLPrinter
import com.sd.laborator.interfaces.JSONPrinter
import com.sd.laborator.interfaces.RawPrinter
import com.sd.laborator.model.Book
import org.springframework.stereotype.Service

@Service
class LibraryHTMLPrinter : HTMLPrinter {
    override fun printHTML(books: Set<Book>): String {
        var content: String = "<html><head><title>Libraria mea HTML</title></head><body>"
        books.forEach {
            content += "<p><h3>${it.name}</h3><h4>${it.author}</h4><h5>${it.publisher}</h5>${it.content}</p><br/>"
        }
        content += "</body></html>"
        return content
    }

}

@Service
class LibraryJSONPrinter : JSONPrinter {
    override fun printJSON(books: Set<Book>): String {
        var content: String = "[\n"
        books.forEach {
            if (it != books.last())
                content += "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.author}\", \"Editura\":\"${it.publisher}\", \"Text\":\"${it.content}\"},\n"
            else
                content += "    {\"Titlu\": \"${it.name}\", \"Autor\":\"${it.author}\", \"Editura\":\"${it.publisher}\", \"Text\":\"${it.content}\"}\n"
        }
        content += "]\n"
        return content
    }
}

@Service
class LibraryRawPrinter : RawPrinter {
    override fun printRaw(books: Set<Book>): String {
        var content: String = ""
        books.forEach {
            content += "${it.name}\n${it.author}\n${it.publisher}\n${it.content}\n\n"
        }
        return content
    }

}