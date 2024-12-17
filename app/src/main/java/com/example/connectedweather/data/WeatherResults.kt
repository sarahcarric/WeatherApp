package com.example.connectedweather.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class WeatherResults(
    @Json(name="list")val items:List<ForecastPeriod>
):java.io.Serializable
