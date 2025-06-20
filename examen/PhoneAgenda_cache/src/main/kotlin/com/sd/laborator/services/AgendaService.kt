package com.sd.laborator.services

import com.sd.laborator.interfaces.IAgendaCacheService
import com.sd.laborator.interfaces.IAgendaService
import com.sd.laborator.pojo.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class AgendaService : IAgendaService {
    companion object {
        val initialAgenda = arrayOf(
            Person(1, "Hello", "Kotlin", "1234"),
            Person(2, "Hello", "Spring", "5678"),
            Person(3, "Hello", "Microservice", "9101112")
        )
    }

    @Autowired
    private lateinit var cacheService: IAgendaCacheService

    private val agenda = ConcurrentHashMap<Int, Person>(
        initialAgenda.associateBy { person: Person -> person.id }
    )

    override fun getPerson(id: Int): Person? {
        val cached = cacheService.getCachedPerson(id)
        if (cached != null) {
            println("Returned person $id from cache.")
            return cached
        }

        val person = agenda[id]
        if (person != null) {
            cacheService.storePersonInCache(id, person)
        }
        return person
    }

    override fun createPerson(person: Person) {
        agenda[person.id] = person
        cacheService.storePersonInCache(person.id, person)
    }

    override fun deletePerson(id: Int) {
        agenda.remove(id)
        cacheService.invalidatePerson(id)
    }

    override fun updatePerson(id: Int, person: Person) {
        deletePerson(id)
        createPerson(person)
    }

    override fun searchAgenda(
        lastNameFilter: String,
        firstNameFilter: String,
        telephoneNumberFilter: String
    ): List<Person> {
        val key = cacheService.generateKey(lastNameFilter, firstNameFilter, telephoneNumberFilter)

        val cachedResult = cacheService.getCachedResult(key)
        if (cachedResult != null) {
            println("Returned agenda result from cache.")
            return cachedResult
        }

        val result = agenda.filter {
            it.value.lastName.lowercase(Locale.getDefault()).contains(lastNameFilter.lowercase()) &&
                    it.value.firstName.lowercase(Locale.getDefault()).contains(firstNameFilter.lowercase()) &&
                    it.value.telephoneNumber.contains(telephoneNumberFilter)
        }.map { it.value }.toList()

        cacheService.storeInCache(key, result)
        return result
    }
}
