package com.sd.laborator.business.interfaces

import com.sd.laborator.business.models.Book

interface ILibraryRepo {
    // Create
    fun createTable()
    fun add(book: Book)

    // Retrieve
    fun getAll(): MutableList<Book?>
    fun getByAuthor(autor: String): MutableList<Book?>
    fun getByPrice(price: Float): MutableList<Book?>

    // Update
    fun update(book: Book)

    // Delete
    fun delete(title: String)
}