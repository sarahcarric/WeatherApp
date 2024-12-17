package com.example.connectedweather

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectedweather.api.WeatherService
import com.example.connectedweather.data.ForecastPeriod
import com.example.connectedweather.data.Weather
import com.example.connectedweather.data.WeatherResults
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val weatherService = WeatherService.create()
    private val forecastAdapter = ForecastAdapterList(::onCardClick)

    private lateinit var searchResultsRV: RecyclerView
    private lateinit var errorMessageTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        errorMessageTV = findViewById(R.id.tv_error_message)
        loadingIndicator = findViewById(R.id.loading_indicator)

        searchResultsRV = findViewById(R.id.rv_forecast_list)
        searchResultsRV.layoutManager = LinearLayoutManager(this)
        searchResultsRV.setHasFixedSize(true)
        searchResultsRV.adapter = forecastAdapter

        val city = "Corvallis"
        doWeatherSearch(city)
    }

    private fun doWeatherSearch(city:String) {
        loadingIndicator.visibility= View.VISIBLE

        weatherService.searchCities(city).enqueue(object: Callback<WeatherResults> {
            override fun onResponse(call: Call<WeatherResults>, response: Response<WeatherResults>) {
                loadingIndicator.visibility = View.INVISIBLE
                Log.d("MainActivity", "Status Code: ${response.code()}")
                if (response.isSuccessful) {
                    forecastAdapter.updateWeather(response.body()?.items)
                    searchResultsRV.visibility = View.VISIBLE
                    errorMessageTV.visibility = View.INVISIBLE

                }
                else{
                    errorMessageTV.visibility = View.VISIBLE
                    errorMessageTV.text = getString(
                        R.string.search_error,
                        response.errorBody()?.string() ?: "unknown error")
                }
            }

            override fun onFailure(call: Call<WeatherResults>, t: Throwable) {
                Log.d(TAG, "Error executing query '$city': ${t.message}")
                loadingIndicator.visibility = View.INVISIBLE
                errorMessageTV.visibility = View.VISIBLE
                errorMessageTV.text = getString(R.string.search_error, t.message)

            }
        })
    }

    private fun onCardClick(card:ForecastPeriod){
        val intent=Intent(this,WeatherDetailActivity::class.java)
        intent.putExtra(EXTRA_WEATHER_CARD,card)
        startActivity(intent)
    }
}
