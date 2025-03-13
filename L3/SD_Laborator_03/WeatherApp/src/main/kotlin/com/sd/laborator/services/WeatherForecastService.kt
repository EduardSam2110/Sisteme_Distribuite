package com.sd.laborator.services

import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL
import kotlin.math.roundToInt

@Service
class WeatherForecastService (private val timeService: TimeService) : WeatherForecastInterface {
    override fun getForecastData(details: Pair<Int?, (Pair<Double?, Double?>)?>): WeatherForecastData {

        val latitude = details.second?.first
        val longitude = details.second?.second
        val locationId = details.first

        // ID-ul locaţiei nu trebuie codificat, deoarece este numeric
        val forecastDataURL = URL("https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,cloud_cover,visibility,wind_speed_10m,wind_direction_10m")

        // preluare conţinut răspuns HTTP la o cerere GET către URL-ul de mai sus
        val rawResponse: String = forecastDataURL.readText()

        // parsare obiect JSON primit
        val responseRootObject = JSONObject(rawResponse)
        val weatherDataObject = responseRootObject.getJSONObject("current")

        // construire şi returnare obiect POJO care încapsulează datele meteo
        return WeatherForecastData(
            //location = responseRootObject.getString("title"),
            date = timeService.getCurrentTime(),
            currentTemp = weatherDataObject.getDouble("temperature_2m"),
            relativeHumidity = weatherDataObject.getDouble("relative_humidity_2m"),
            apparentTemperature = weatherDataObject.getDouble("apparent_temperature"),
            precipitationProbability = weatherDataObject.getDouble("precipitation_probability"),
            cloudCover = weatherDataObject.getInt("cloud_cover"),
            visibility = weatherDataObject.getInt("visibility"),
            windDirection = weatherDataObject.getInt("wind_direction_10m"),
            windSpeed = weatherDataObject.getDouble("wind_speed_10m")
        )
    }
}