package com.example.connectedweather.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)

data class Weather(
    @Json(name="description")val shortDesc:String
) :java.io.Serializable