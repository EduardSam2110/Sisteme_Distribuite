package com.sd.laborator.services

import org.json.JSONObject
import java.net.URL

class PublicIPService {
    fun getUserPublicIP() : String {

        val rawResponse = URL("https://api64.ipify.org/?format=json").readText()
        val responseRootObject = JSONObject(rawResponse)
        val ipAddress = responseRootObject.getString("ip")

        return ipAddress
    }
}