package com.example.connectedweather.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ForecastResults(
    @Json(name="list")val forecast:List<ForecastPeriod>

):java.io.Serializable
