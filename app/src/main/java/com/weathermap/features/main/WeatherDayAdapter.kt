package com.weathermap.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weathermap.R
import com.weathermap.data.models.DailyWeatherModel
import com.weathermap.databinding.WeatherDayItemBinding
import com.weathermap.utils.Utils

class WeatherDayAdapter(private val data: ArrayList<DailyWeatherModel>) :
    RecyclerView.Adapter<WeatherDayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<WeatherDayItemBinding>(
            LayoutInflater.from(parent.context), R.layout.weather_day_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(private val binding: WeatherDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(dailyWeatherModel: DailyWeatherModel) {
            binding.tvDate.text = Utils.getDate(dailyWeatherModel.dt)
            binding.tvDate.contentDescription = Utils.getDate(dailyWeatherModel.dt)
            binding.tvAT.text = "${dailyWeatherModel.temp.eve}\u2103"
            binding.tvAT.contentDescription = "${dailyWeatherModel.temp.eve}\u2103"
            binding.tvDescription.text = dailyWeatherModel.weather[0].description
            binding.tvDescription.contentDescription = dailyWeatherModel.weather[0].description
            binding.tvHumidity.text = "${dailyWeatherModel.humidity}%"
            binding.tvHumidity.contentDescription = "${dailyWeatherModel.humidity}%"
            binding.tvPressure.text = dailyWeatherModel.pressure.toString()
            binding.tvPressure.contentDescription = dailyWeatherModel.pressure.toString()
        }
    }
}