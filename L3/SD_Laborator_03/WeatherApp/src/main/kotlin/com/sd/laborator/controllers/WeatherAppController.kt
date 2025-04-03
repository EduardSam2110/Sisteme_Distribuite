package com.sd.laborator.controllers


import com.sd.laborator.interfaces.CheckPermissionInterace
import com.sd.laborator.interfaces.GeolocationInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.CheckPermissionService
import com.sd.laborator.services.WeatherOrchestrator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/*
am folosit spring-boot:run -Dspring-boot.run.arguments=--server.address=0.0.0.0 pentru a rula in reteaua locala

http://192.168.1.249:8080/getforecast/Bucuresti

 */

//Chaining
//@Controller
//class WeatherAppController {
//    @Autowired
//    private lateinit var locationSearchService: LocationSearchInterface
//
//    @Autowired
//    private lateinit var weatherForecastService: WeatherForecastInterface
//
//    @Autowired
//    private lateinit var geolocationService: GeolocationInterface
//
//    @Autowired
//    private lateinit var checkPermission: CheckPermissionInterace
//
//    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
//    @ResponseBody
//    fun getForecast(@PathVariable location: String): String {
//
//        val userLocationData = geolocationService.getUserGeolocation() // datele despre user: codul tarii, IP si mesajul de afisat pe site
//
//        val locationDetails = locationSearchService.getLocationId(location) // datele despre locatia cautata: codul, latitudinea si longitudinea
//
//        // dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
//        if (locationDetails.first.first == -1) {
//            return "Nu s-au putut gasi date meteo pentru cuvintele cheie \"$location\"!"
//        }
//
//        if(checkPermission.checkPermission(userLocationData[0], locationDetails.first.second))
//        {
//            val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(locationDetails)
//
//            return rawForecastData.toString() + userLocationData[2]
//        }
//        else
//        {
//            return "Nu aveti acces pentru a obtine informatii meteo din \"$location\"!"
//        }
//    }
//}


//Orchestration
@Controller
class WeatherAppController(private val weatherOrchestrator: WeatherOrchestrator) {
    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
        return weatherOrchestrator.getWeatherForecast(location)
    }
}



/*
@Controller
class WeatherAppController(private val weatherOrchestrator: WeatherOrchestrator) {
    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String, request: HttpServletRequest): String {
        return weatherOrchestrator.getWeatherForecast(location, request)
    }
}
 */