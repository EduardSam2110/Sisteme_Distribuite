package com.sd.laborator.business.persistence

import com.sd.laborator.business.interfaces.ILibraryRepo
import com.sd.laborator.business.models.Book
import com.sd.laborator.business.models.Content
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException
import org.springframework.jdbc.core.RowMapper

class LibrayRowMapper : RowMapper<Book> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Book {
        return Book(Content(rs.getString("author"), rs.getString("text"), rs.getString("name"), rs.getString("publisher")))
    }
}

@Repository
class LibraryRepo : ILibraryRepo {
    @Autowired
    private lateinit var _jdbcTemplate: JdbcTemplate
    private var _rowMapper: RowMapper<Book> = LibrayRowMapper()

    override fun createTable() {
        _jdbcTemplate.execute("DROP TABLE IF EXISTS book")
        _jdbcTemplate.execute(
            """CREATE TABLE book (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            author VARCHAR(100),
            name VARCHAR(100),
            publisher VARCHAR(100),
            text TEXT
        )"""
        )
    }

    override fun add(book: Book) {
        try {
            _jdbcTemplate.update("INSERT INTO book(author, name, publisher, text) VALUES (?, ?, ?, ?)", book.author, book.name, book.publisher, book.content)
        } catch (e: UncategorizedSQLException){
            println("An error has occurred in ${this.javaClass.name}.add")
        }
    }

    override fun getAll(): MutableList<Book?> {
        return _jdbcTemplate.query("SELECT * FROM book", _rowMapper)
    }

    override fun getByAuthor(autor: String): MutableList<Book?> {
        return _jdbcTemplate.query("SELECT * FROM book WHERE author = '$autor'", _rowMapper)
    }

    override fun getByPrice(price: Float): MutableList<Book?> {
        return _jdbcTemplate.query("SELECT * FROM book WHERE price <= $price", _rowMapper)
    }

    override fun update(book: Book) {
        try {
            _jdbcTemplate.update("UPDATE book SET name = ?, price = ? WHERE author = ?", book.author, book.name, book.publisher, book.content)
        } catch (e: UncategorizedSQLException){
            println("An error has occurred in ${this.javaClass.name}.update")
        }
    }

    override fun delete(title: String) {
        try {
            _jdbcTemplate.update("DELETE FROM book WHERE title = ?", title)
        } catch (e: UncategorizedSQLException){
            println("An error has occurred in ${this.javaClass.name}.delete")
        }
    }
}