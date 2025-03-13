package com.sd.laborator.interfaces

interface LocationSearchInterface {
    fun getLocationId(locationName: String): Pair<Int?, (Pair<Double?, Double?>)?>
}