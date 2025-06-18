package com.sd.laborator

import io.micronaut.function.FunctionBean
import io.micronaut.function.executor.FunctionInitializer
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.function.Function

@FunctionBean("eratostene")
class EratosteneFunction : FunctionInitializer(), Function<EratosteneRequest, EratosteneResponse> {
    @Inject
    private lateinit var eratosteneSieveService: EratosteneSieveService

    private val LOG: Logger = LoggerFactory.getLogger(EratosteneFunction::class.java)

    override fun apply(msg: EratosteneRequest): EratosteneResponse {
        // preluare numar din parametrul de intrare al functiei
        val number = msg.getNumber()
        val operation = msg.getOperation()

        val response = EratosteneResponse()

        // se verifica daca numarul nu depaseste maximul
        if (number >= eratosteneSieveService.MAX_SIZE) {
            LOG.error("Parametru prea mare! $number > maximul de ${eratosteneSieveService.MAX_SIZE}")
            response.setMessage("Se accepta doar parametri mai mici ca " + eratosteneSieveService.MAX_SIZE)
            return response
        }

        // Determine which operation to perform based on the operation parameter
        return when (operation) {
            "primes" -> {
                LOG.info("Se calculeaza primele $number numere prime ...")

                // Calculate primes - uncommented code
                response.setPrimes(eratosteneSieveService.findPrimesLessThan(number))
                response.setMessage("Calcul efectuat cu succes!")

                LOG.info("Calcul incheiat!")
                response
            }
            "union" -> {
                LOG.info("Se calculeaza uniunea multimilor ...")

                // Calculate union - your current logic
                var A = Multime(number)
                var B = Multime(number)

                response.setA(A)
                response.setB(B)
                response.setC(eratosteneSieveService.union(A, B))
                response.setMessage("Valori pentru A si B Generate!")

                LOG.info("Calcul incheiat!")
                response
            }
            "intersect" -> {
                LOG.info("Se calculeaza intersectia multimilor ...")

                // Calculate union - your current logic
                var A = Multime(number)
                var B = Multime(number)

                response.setA(A)
                response.setB(B)
                response.setC(eratosteneSieveService.intersect(A, B))
                response.setMessage("Valori pentru A si B Generate!")

                LOG.info("Calcul incheiat!")
                response
            }
            else -> {
                LOG.error("Operațiune necunoscută: $operation")
                response.setMessage("Operațiune necunoscută: $operation. Folosește 'primes' sau 'union'")
                response
            }
        }
    }
}

/**
 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
 * where the argument to echo is the JSON to be parsed.
 */
fun main(args: Array<String>) {
    val function = EratosteneFunction()
    function.run(args, { context -> function.apply(context.get(EratosteneRequest::class.java)) })
}