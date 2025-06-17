package com.sd.laborator.services

import com.sd.laborator.interfaces.OrchestrationServiceInterface
import com.sd.laborator.pojo.WeatherForecastData
import org.springframework.stereotype.Service

@Service
class OrchestrationService: OrchestrationServiceInterface {
    private val filelog = FileLogger()

    override fun orch(input: WeatherForecastData): String {
        val city = ExtractCityFilter().process(input)
        val temp_pair = ExtractTemperaturesFilter().process(input)

        val value =  FormatOutputFilter().process(
            Pair(
                city,
                temp_pair
            )
        )

        filelog.writeToFile("$city.txt", value)

        return value
    }
}