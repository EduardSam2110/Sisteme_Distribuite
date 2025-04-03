package com.sd.laborator.business.services;

import com.sd.laborator.business.interfaces.ILibraryDAOService
import com.sd.laborator.business.models.Book
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


@Service
public class LibraryDAOServiceDB : ILibraryDAOService {

    @Throws(Exception::class)
    fun connectToDB() {
        val url = "jdbc:sql" // Database details
        val username = "rootgfg" // MySQL credentials
        val password = "gfg123"
        val query = "select * from students" // Query to be run

        // Load and register the driver
        Class.forName("com.mysql.cj.jdbc.Driver")

        // Establish connection
        val con: Connection = DriverManager.getConnection(url, username, password)
        println("Connection Established successfully")

        // Create a statement
        val st: Statement = con.createStatement()

        // Execute the query
        val rs: ResultSet = st.executeQuery(query)

        // Process the results
        while (rs.next()) {
            val name = rs.getString("name") // Retrieve name from db
            println(name) // Print result on console
        }

        // Close the statement and connection
        st.close()
        con.close()
        println("Connection Closed....")
    }
    override fun getBooks(): Set<Book> {
        TODO("Not yet implemented")
    }

    override fun addBook(book: Book) {
        TODO("Not yet implemented")
    }

    override fun findAllByAuthor(author: String): Set<Book> {
        TODO("Not yet implemented")
    }

    override fun findAllByTitle(title: String): Set<Book> {
        TODO("Not yet implemented")
    }

    override fun findAllByPublisher(publisher: String): Set<Book> {
        TODO("Not yet implemented")
    }

}


