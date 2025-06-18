package com.sd.laborator

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(Application::class.java, *args)
    }

    @Controller
    class LambdaController {
        @Post
        fun execute(@Body request: EratosteneRequest): EratosteneResponse {
            request.setOperation("primes")  // Set operation to primes
            return handler.apply(request)
        }

        @Post("/unire")
        fun unire(@Body request: EratosteneRequest): EratosteneResponse {
            request.setOperation("union")   // Set operation to union
            return handler.apply(request)
        }

        @Post("/intersectie")
        fun interesct(@Body request: EratosteneRequest): EratosteneResponse {
            request.setOperation("intersect")   // Set operation to intersection
            return handler.apply(request)
        }

        companion object {
            private val handler = EratosteneFunction()
        }
    }
}