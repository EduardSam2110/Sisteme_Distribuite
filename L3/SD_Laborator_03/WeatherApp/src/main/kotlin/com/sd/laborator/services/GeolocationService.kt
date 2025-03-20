package com.sd.laborator.services

import com.sd.laborator.interfaces.GeolocationInterface
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL

@Service
class GeolocationService : GeolocationInterface{
    override fun getUserGeolocation(): List<String> {
        val publicIp = PublicIPService()
        val ip = publicIp.getUserPublicIP()

        val geolocationSearch = URL("http://ipinfo.io/${ip}/json")
        val rawResponse: String = geolocationSearch.readText()

        val responseRootObject = JSONObject(rawResponse)
        val userCountry = responseRootObject.getString("country")

        val message = "Te afli in $userCountry iar adresa ta ip este $ip"

        return listOf(userCountry, ip, message)
    }
}