package com.sd.laborator.services

import com.sd.laborator.interfaces.FilterInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.stereotype.Service

@Service
class ExtractCityFilter : FilterInterface<WeatherForecastData, String> {
    override fun process(input: WeatherForecastData): String = input.city
}