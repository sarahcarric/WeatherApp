package com.example.connectedweather.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class Main(
    @Json(name="temp_min") val lowTemp:Float,
    @Json(name="temp_max") val highTemp:Float,
):java.io.Serializable
