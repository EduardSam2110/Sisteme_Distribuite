package com.sd.laborator.business.services

import com.sd.laborator.business.interfaces.ILibraryRepo
import com.sd.laborator.business.models.Book
import com.sd.laborator.business.models.Content
import com.sd.laborator.business.persistence.LibraryRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class BookService {
    @Autowired
    private lateinit var libraryRepo: ILibraryRepo

    private var _books: MutableSet<Book> = mutableSetOf(
        Book(
            Content(
                "Roberto Ierusalimschy",
                "Preface. When Waldemar, Luiz, and I started the development of Lua, back in 1993, we could hardly imagine that it would spread as it did. ...",
                "Programming in LUA",
                "Teora"
            )
        ),
        Book(
            Content(
                "Jules Verne",
                "Nemaipomeniti sunt francezii astia! - Vorbiti, domnule, va ascult! ....",
                "Steaua Sudului",
                "Corint"
            )
        ),
        Book(
            Content(
                "Jules Verne",
                "Cuvant Inainte. Imaginatia copiilor - zicea un mare poet romantic spaniol - este asemenea unui cal nazdravan, iar curiozitatea lor e pintenul ce-l fugareste prin lumea celor mai indraznete proiecte.",
                "O calatorie spre centrul pamantului",
                "Polirom"
            )
        ),
        Book(
            Content(
                "Jules Verne",
                "Partea intai. Naufragiatii vazduhului. Capitolul 1. Uraganul din 1865. ...",
                "Insula Misterioasa",
                "Teora"
            )
        ),
        Book(
            Content(
                "Jules Verne",
                "Capitolul I. S-a pus un premiu pe capul unui om. Se ofera premiu de 2000 de lire ...",
                "Casa cu aburi",
                "Albatros"
            )
        )
    )

     fun createBookTable() {
         libraryRepo.createTable()

         for(b in _books)
            libraryRepo.add(b)
    }

     fun addBook(book: Book) {
        libraryRepo.add(book)
    }

     fun getBooks(): String {
        val result: MutableList<Book?> = libraryRepo.getAll()
        var stringResult: String = ""
        for (item in result) {
            stringResult += item
        }
        return stringResult
    }

     fun getBookByAuthor(author: String): Set<Book?> {
        val result = libraryRepo.getByAuthor(author)
        return result.toSet()
    }

//     fun getBeerByPrice(price: Float): String {
//        val result = libraryRepo.getByPrice(price)
//        var stringResult: String = ""
//        for (item in result) {
//            stringResult += item
//        }
//        return stringResult
//    }

     fun updateBook(book: Book) {
        libraryRepo.update(book)
    }

     fun deleteBook(title: String) {
        libraryRepo.delete(title)
    }
}