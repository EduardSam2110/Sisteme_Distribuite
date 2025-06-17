package com.sd.laborator.controllers
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.OrchestrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class WeatherAppAPI {
    @Autowired
    private lateinit var locationSearchService: LocationSearchInterface

    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    fun getForecast(@PathVariable location: String): ResponseEntity<String> {
        // se incearca preluarea WOEID-ului locaţiei primite in URL
        val details = locationSearchService.getLocationId(location)

        val latitude = details.second?.first
        val longitude = details.second?.second
        val locationId = details.first.first

        var resp = ""
        // dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
        val status = if (locationId == -1) {
            HttpStatus.NOT_FOUND
        } else {
            HttpStatus.OK
        }

        if (locationId != -1) {
            // pe baza ID-ului de locaţie, se interoghează al doilea serviciu care returnează datele meteo
            // încapsulate într-un obiect POJO
            val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(details)
            val forecastWithCity = rawForecastData.copy(city = location)

            val orch = OrchestrationService()
            orch.orch(forecastWithCity)

            // fiind obiect POJO, funcţia toString() este suprascrisă pentru o afişare mai prietenoasă
            resp = forecastWithCity.toString()
        }

        return ResponseEntity(resp, status)
    }
}