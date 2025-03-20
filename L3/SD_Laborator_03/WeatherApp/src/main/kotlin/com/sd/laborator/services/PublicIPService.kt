package com.sd.laborator.services

import com.sd.laborator.interfaces.PublicIPInterace
import org.json.JSONObject
import java.net.URL

class PublicIPService : PublicIPInterace{
    override fun getUserPublicIP() : String {

        val rawResponse = URL("https://api64.ipify.org/?format=json").readText()
        val responseRootObject = JSONObject(rawResponse)
        val ipAddress = responseRootObject.getString("ip")

        return ipAddress
    }
}