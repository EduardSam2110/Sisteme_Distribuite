package com.sd.laborator.services;

import com.sd.laborator.interfaces.FilterInterface
import com.sd.laborator.pojo.WeatherForecastData;
import org.springframework.stereotype.Service;

@Service
class ExtractTemperaturesFilter : FilterInterface<WeatherForecastData, Pair<Double,Double>> {
    override fun process(input: WeatherForecastData): Pair<Double, Double> =
        Pair(input.temp_min, input.temp_max)
}