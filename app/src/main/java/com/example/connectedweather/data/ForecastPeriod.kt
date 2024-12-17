package com.example.connectedweather.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//will have to change to @JSON after having been parsed
@JsonClass(generateAdapter = true)
data class ForecastPeriod(
    @Json(name="main")val mainClass:Main,
    @Json(name="weather")val weatherClass:List<Weather>,
    @Json(name="dt_txt")val date:String,
    @Json(name="pop")val precipitation:Double,
):java.io.Serializable

