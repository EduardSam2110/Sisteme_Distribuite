package com.sd.laborator.services

import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

@Service
class AgendaCacheService {
    private val agendaCache = ConcurrentHashMap<String, Pair<List<Person>, LocalDateTime>>()
    private val personCache = ConcurrentHashMap<Int, Pair<Person, LocalDateTime>>()
    private val ttlMinutes = 1L

    // --------- Cache pentru /agenda (căutare complexă) ---------

    fun getCachedResult(key: String): List<Person>? {
        val entry = agendaCache[key]
        if (entry != null) {
            val (data, timestamp) = entry
            if (timestamp.plusMinutes(ttlMinutes).isAfter(LocalDateTime.now())) {
                return data
            } else {
                agendaCache.remove(key)
            }
        }
        return null
    }

    fun storeInCache(key: String, data: List<Person>) {
        agendaCache[key] = Pair(data, LocalDateTime.now())
    }

    fun generateKey(lastName: String, firstName: String, telephone: String): String {
        return "$lastName|$firstName|$telephone".lowercase()
    }

    // --------- Cache pentru /person/{id} ---------

    fun getCachedPerson(id: Int): Person? {
        val entry = personCache[id]
        if (entry != null) {
            val (person, timestamp) = entry
            if (timestamp.plusMinutes(ttlMinutes).isAfter(LocalDateTime.now())) {
                return person
            } else {
                personCache.remove(id)
            }
        }
        return null
    }

    fun storePersonInCache(id: Int, person: Person) {
        personCache[id] = Pair(person, LocalDateTime.now())
    }

    fun invalidatePerson(id: Int) {
        personCache.remove(id)
    }

    fun invalidateAll() {
        agendaCache.clear()
        personCache.clear()
    }
}
