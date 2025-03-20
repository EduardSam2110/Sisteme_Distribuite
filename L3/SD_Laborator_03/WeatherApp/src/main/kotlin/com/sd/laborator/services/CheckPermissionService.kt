package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckPermissionInterace
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.io.File

@Service
class CheckPermissionService : CheckPermissionInterace{
    override fun checkPermission(userCountry: String?, searchedCountryCode : String?) : Boolean {
        val jsonContent = File("interzis.json").readText()
        val responseRootObject = JSONObject(jsonContent)
        val country = responseRootObject.getString("country")
        val availableCountries = responseRootObject.getJSONArray("available")

        if(userCountry == country)
        {
            if(searchedCountryCode in availableCountries)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else
            return false
    }
}