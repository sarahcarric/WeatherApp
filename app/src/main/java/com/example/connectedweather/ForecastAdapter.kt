package com.example.connectedweather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectedweather.data.ForecastPeriod

import java.util.*


class ForecastAdapterList(
    private val onCardClick:(ForecastPeriod)->Unit
):RecyclerView.Adapter<ForecastAdapterList.ViewHolder>(){
    var forecastPeriods=listOf<ForecastPeriod>()

    fun updateWeather(newWeather:List<ForecastPeriod>?){
        forecastPeriods=newWeather?: listOf()
        notifyDataSetChanged()
    }
        override fun getItemCount() = this.forecastPeriods.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_list_item, parent, false)
            return ViewHolder(view,onCardClick)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(this.forecastPeriods[position])
        }

        class ViewHolder(view: View,   private val onClick:(ForecastPeriod)->Unit
        ) : RecyclerView.ViewHolder(view) {
            private val monthTV: TextView = view.findViewById(R.id.tv_month)
            private val dayTV: TextView = view.findViewById(R.id.tv_day)
            private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
            private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
            private val shortDescTV: TextView = view.findViewById(R.id.tv_short_description)
            private val popTV: TextView = view.findViewById(R.id.tv_pop)
            private lateinit var currentForecastPeriod: ForecastPeriod

        init {
            Log.d("ViewHolder","A card was clicked")
            itemView.setOnClickListener {
              currentForecastPeriod.let(onClick)
            }
        }

            fun bind(forecastPeriod: ForecastPeriod) {
                currentForecastPeriod = forecastPeriod

                val cal = Calendar.getInstance()

                monthTV.text =
                    cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
                dayTV.text = cal.get(Calendar.DAY_OF_MONTH).toString()
                highTempTV.text = forecastPeriod.mainClass.highTemp.toString() + "°F"
                lowTempTV.text = forecastPeriod.mainClass.lowTemp.toString() + "°F"
                popTV.text = (forecastPeriod.precipitation * 100.0).toInt().toString() + "% precip."
                shortDescTV.text = forecastPeriod.weatherClass[0].shortDesc
            }
        }
    }