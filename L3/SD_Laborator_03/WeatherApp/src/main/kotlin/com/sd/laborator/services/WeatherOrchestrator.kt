package com.sd.laborator.services

import com.sd.laborator.interfaces.CheckPermissionInterace
import com.sd.laborator.interfaces.GeolocationInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class WeatherOrchestrator {
    @Autowired
    private lateinit var locationSearchService: LocationSearchInterface

    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

    @Autowired
    private lateinit var geolocationService: GeolocationInterface

    @Autowired
    private lateinit var checkPermission: CheckPermissionInterace

    fun getWeatherForecast(location: String): String {
        val userLocationData = geolocationService.getUserGeolocation() // datele despre user: codul tarii, IP si mesajul de afisat pe site

        val locationDetails = locationSearchService.getLocationId(location) // datele despre locatia cautata: codul locatiei si country code, latitudinea si longitudinea

        print(locationDetails.first.first)

        if (locationDetails.first.first == -1) {
            return "Nu s-au putut gasi date meteo pentru cuvintele cheie \"$location\"!"
        }

        if(checkPermission.checkPermission(userLocationData[0], locationDetails.first.second))
        {
            val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(locationDetails)

            return rawForecastData.toString() + userLocationData[2]
        }
        else
        {
            return "Nu aveti acces pentru a obtine informatii meteo din \"$location\"!"
        }
    }
}