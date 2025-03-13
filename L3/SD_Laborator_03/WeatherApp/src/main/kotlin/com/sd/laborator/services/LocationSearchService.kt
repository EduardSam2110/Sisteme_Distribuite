package com.sd.laborator.services

import com.sd.laborator.interfaces.LocationSearchInterface
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class LocationSearchService : LocationSearchInterface {
    override fun getLocationId(locationName: String): Pair<Int?, (Pair<Double?, Double?>)?> {
        // codificare parametru URL (deoarece poate conţine caractere speciale)
        val encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())

        // construire obiect de tip URL
        val locationSearchURL = URL("https://geocoding-api.open-meteo.com/v1/search?name=${encodedLocationName}&count=2&language=en&format=json")

        // preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = locationSearchURL.readText()

        // parsare obiect JSON
        val responseRootObject = JSONObject("${rawResponse}}")
        val responseContentObject = responseRootObject.getJSONArray("results").takeUnless { it.isEmpty }
            ?.getJSONObject(0)
        val latitude = responseContentObject?.getDouble("latitude")?.toDouble();
        val longitude = responseContentObject?.getDouble("longitude")?.toDouble();
        val locationId = responseContentObject?.getInt("id")?.toInt();

        return Pair<Int?, (Pair<Double?, Double?>)?>(locationId,Pair<Double?,Double?>(latitude, longitude))
    }

    override fun getCountryCode(locationName: String): String? {
        // codificare parametru URL (deoarece poate conţine caractere speciale)
        val encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())

        // construire obiect de tip URL
        val locationSearchURL = URL("https://geocoding-api.open-meteo.com/v1/search?name=${encodedLocationName}&count=2&language=en&format=json")

        // preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = locationSearchURL.readText()

        // parsare obiect JSON
        val responseRootObject = JSONObject("${rawResponse}}")
        val responseContentObject = responseRootObject.getJSONArray("results").takeUnless { it.isEmpty }
            ?.getJSONObject(0)
        val countryCode = responseContentObject?.getString("country_code")?.toString();

        return countryCode
    }
}