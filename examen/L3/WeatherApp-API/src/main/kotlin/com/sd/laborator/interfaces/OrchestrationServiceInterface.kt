package com.sd.laborator.interfaces

import com.sd.laborator.pojo.WeatherForecastData

interface OrchestrationServiceInterface {
    fun orch(input: WeatherForecastData): String
}