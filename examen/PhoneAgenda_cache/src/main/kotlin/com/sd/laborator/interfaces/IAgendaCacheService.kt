package com.sd.laborator.interfaces


import com.sd.laborator.pojo.Person

interface IAgendaCacheService {
    fun getCachedResult(key: String): List<Person>?
    fun storeInCache(key: String, data: List<Person>)
    fun generateKey(lastName: String, firstName: String, telephone: String): String

    fun getCachedPerson(id: Int): Person?
    fun storePersonInCache(id: Int, person: Person)
    fun invalidatePerson(id: Int)
    fun invalidateAll()
}
