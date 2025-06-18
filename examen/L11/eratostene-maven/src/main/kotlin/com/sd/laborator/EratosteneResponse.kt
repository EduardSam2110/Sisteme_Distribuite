package com.sd.laborator

import io.micronaut.core.annotation.Introspected

@Introspected
class EratosteneResponse {
	private var message: String? = null
	private var primes: List<Int>? = null
	private var A: Multime? = null
	private var B: Multime? = null
	private var C: Multime? = null

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

	fun getA(): Multime? {
		return A
	}
	fun setA(a: Multime?) {
		this.A = a
	}

	fun getB(): Multime? {
		return B
	}

	fun setB(b: Multime?) {
		this.B = b
	}
	fun getC(): Multime? {
		return C
	}

	fun setC(c: Multime?) {
		this.C = c
	}
}


