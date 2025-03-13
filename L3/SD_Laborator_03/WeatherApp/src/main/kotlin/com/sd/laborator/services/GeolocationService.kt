package com.sd.laborator.services

import com.sd.laborator.interfaces.GeolocationInterface
import org.json.JSONObject
import java.net.URL

class GeolocationService : GeolocationInterface{
    override fun getUserGeolocation(ipAddress: String): String {

        val geolocationSearch = URL("http://ipinfo.io/${ipAddress}/json")
        val rawResponse: String = geolocationSearch.readText()

        val responseRootObject = JSONObject(rawResponse)
        val userLocation = responseRootObject.getString("country")

        return userLocation
    }
}