package com.sd.laborator.services

import com.sd.laborator.interfaces.FilterInterface
import org.springframework.stereotype.Service

@Service
class FormatOutputFilter : FilterInterface<Pair<String, Pair<Double, Double>>, String> {
    override fun process(input: Pair<String, Pair<Double, Double>>): String {
        val (city, temps) = input
        return "Oras: $city\nTemperatura minima: ${temps.first}\nTemperatura maxima: ${temps.second}"
    }
}