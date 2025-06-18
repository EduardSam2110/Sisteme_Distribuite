package com.sd.laborator

import io.micronaut.core.annotation.Introspected

@Introspected
class EratosteneResponse {
	private var message: String? = null
	private var primes: List<Int>? = null
	private var reuninune: Multime? = null

	fun getPrimes(): List<Int>? {
		return primes
	}

	fun setPrimes(primes: List<Int>?) {
		this.primes = primes
	}

	fun getMessage(): String? {
		return message
	}

	fun setMessage(message: String?) {
		this.message = message
	}

	fun setMultime(r : Multime?) {
		this.reuninune = r
	}

	fun getMultime(): Multime? {
		return this.reuninune
	}
}


