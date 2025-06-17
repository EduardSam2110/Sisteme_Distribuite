package com.sd.laborator.interfaces

import com.sd.laborator.pojo.WeatherForecastData

interface WeatherForecastInterface {
    fun getForecastData(details: Pair<(Pair<Int?, String?>), (Pair<Double?, Double?>)?>): WeatherForecastData
}

