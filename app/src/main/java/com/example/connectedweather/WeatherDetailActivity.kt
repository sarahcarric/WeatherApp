package com.example.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.connectedweather.data.ForecastPeriod

const val EXTRA_WEATHER_CARD="EXTRA_WEATHER"
class WeatherDetailActivity : AppCompatActivity() {
    private var card:ForecastPeriod?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        if(intent!=null && intent.hasExtra(EXTRA_WEATHER_CARD)){
            card=intent.getSerializableExtra(EXTRA_WEATHER_CARD) as ForecastPeriod

            findViewById<TextView>(R.id.tv_spec_date).text=card!!.date
            findViewById<TextView>(R.id.tv_spec_descrip).text=card!!.weatherClass[0].shortDesc
            findViewById<TextView>(R.id.tv_spec_hightemp).text=card!!.mainClass.highTemp.toString()+"°F"
            findViewById<TextView>(R.id.tv_spec_precip).text=(card!!.precipitation*100).toString()+"%"
            findViewById<TextView>(R.id.tv_spec_lowtemp).text=card!!.mainClass.lowTemp.toString()+"°F"
        }
    }
}