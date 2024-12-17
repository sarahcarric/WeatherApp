package com.example.connectedweather.api
import com.example.connectedweather.data.WeatherResults
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    fun searchCities(
         @Query ("q") city:String="Corvallis,OR,US",
         @Query("appid") appid:String="2ac1a956d52c698de42c9b9b5a5daf2e",
         @Query("units")units:String="imperial"
    ):Call<WeatherResults>
    companion object{
        private const val BASE_URL="https://api.openweathermap.org/data/2.5/"

        //make a concrete object that implements
            //returns an instance of the Weather interface
        fun create():WeatherService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}