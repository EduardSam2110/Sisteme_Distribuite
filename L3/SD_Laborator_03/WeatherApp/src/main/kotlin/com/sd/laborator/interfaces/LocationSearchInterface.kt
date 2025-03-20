package com.sd.laborator.interfaces

interface LocationSearchInterface {
    fun getLocationId(locationName: String): Pair<(Pair<Int?, String?>), (Pair<Double?, Double?>)?>
//    fun getCountryCode(location: String): String?
}