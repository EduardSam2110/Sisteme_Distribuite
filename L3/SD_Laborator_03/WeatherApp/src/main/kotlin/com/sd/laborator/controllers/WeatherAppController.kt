package com.sd.laborator.controllers

import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.GeolocationService
import com.sd.laborator.services.PublicIPService
import com.sd.laborator.services.TimeService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File

@Controller
class WeatherAppController {
    @Autowired
    private lateinit var locationSearchService: LocationSearchInterface

    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

//    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
//    @ResponseBody
//    fun getForecast(@PathVariable location: String): String {
//        // se incearca preluarea WOEID-ului locaţiei primite in URL
//
//        val details = locationSearchService.getLocationId(location)
//
//        val locationId = details.first
//
//        // dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
//        if (locationId == -1) {
//            return "Nu s-au putut gasi date meteo pentru cuvintele cheie \"$location\"!"
//        }
//
//        // pe baza ID-ului de locaţie, se interoghează al doilea serviciu care returnează datele meteo
//        // încapsulate într-un obiect POJO
//        val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(details)
//
//        // fiind obiect POJO, funcţia toString() este suprascrisă pentru o afişare mai prietenoasă
//        return rawForecastData.toString()
//    }


    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
        // se incearca preluarea WOEID-ului locaţiei primite in URL
        val ip = PublicIPService()
        val geoloc = GeolocationService()

        val userLocation = geoloc.getUserGeolocation(ip.getUserPublicIP())

        val countryCode = locationSearchService.getCountryCode(location)

        val details = locationSearchService.getLocationId(location)
        var locationId = details.first

        val jsonContent = File("interzis.json").readText()
        val responseRootObject = JSONObject(jsonContent)
        val country = responseRootObject.getString("country")
        val availableCountries = responseRootObject.getJSONArray("available")

        // dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
        if (locationId == -1) {
            return "Nu s-au putut gasi date meteo pentru cuvintele cheie \"$location\"!"
        }

        if(userLocation == country)
        {
            if(countryCode in availableCountries)
            {
                locationId = details.first
            }
            else
            {
                locationId = -99;
            }
        }


        if(locationId == -99)
        {
            return "Nu aveti acces pentru a obtine informatii meteo din \"$location\"!"
        }

        // pe baza ID-ului de locaţie, se interoghează al doilea serviciu care returnează datele meteo
        // încapsulate într-un obiect POJO
        val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(details)

        // fiind obiect POJO, funcţia toString() este suprascrisă pentru o afişare mai prietenoasă
        return rawForecastData.toString()
    }

}