package com.sd.laborator.services

import com.sd.laborator.interfaces.IAgendaCacheService
import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

@Service
class AgendaCacheService : IAgendaCacheService {
    private val agendaCache = ConcurrentHashMap<String, Pair<List<Person>, LocalDateTime>>()
    private val personCache = ConcurrentHashMap<Int, Pair<Person, LocalDateTime>>()
    private val ttlMinutes = 1L

    override fun getCachedResult(key: String): List<Person>? {
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

    override fun storeInCache(key: String, data: List<Person>) {
        agendaCache[key] = Pair(data, LocalDateTime.now())
    }

    override fun generateKey(lastName: String, firstName: String, telephone: String): String {
        return "$lastName|$firstName|$telephone".lowercase()
    }

    override fun getCachedPerson(id: Int): Person? {
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

    override fun storePersonInCache(id: Int, person: Person) {
        personCache[id] = Pair(person, LocalDateTime.now())
    }

    override fun invalidatePerson(id: Int) {
        personCache.remove(id)
    }

    override fun invalidateAll() {
        agendaCache.clear()
        personCache.clear()
    }
}
