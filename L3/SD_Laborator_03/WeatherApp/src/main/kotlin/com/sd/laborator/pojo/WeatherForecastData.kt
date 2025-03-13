package com.sd.laborator.pojo

import java.util.Date


data class WeatherForecastData (
    var date: String,
    var currentTemp: Double,
    var relativeHumidity: Double,
    var apparentTemperature: Double,
    var precipitationProbability: Double,
    var cloudCover: Int,
    var visibility: Int,
    var windSpeed: Double,
    var windDirection: Int
)
