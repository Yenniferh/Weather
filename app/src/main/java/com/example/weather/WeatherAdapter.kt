package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.City
import com.example.weather.data.Forecast
import com.example.weather.databinding.WeatherListItemBinding
import java.util.zip.Inflater

class WeatherAdapter(private val data : List<Forecast>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder : WeatherListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.weather_list_item, parent, false)
        return ViewHolder(binder)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemWeather.forecast = item
        holder.itemWeather.executePendingBindings()
        /*holder.image.setImageResource(
            when (item.currentWeather) {
                "Sunny" -> R.drawable.sun_summer_sunny
                "Hot" -> R.drawable.summer_hot_sun_umbrella
                "Cloud" -> R.drawable.sun_sunny_cloud
                "overcast clouds" -> R.drawable.sun_sunny_cloud
                "scattered clouds" -> R.drawable.sun_sunny_cloud
                "Rain" -> R.drawable.rain_rainy_heavy_cloud
                "light rain" -> R.drawable.rain_rainy_heavy_cloud
                "ThunderStorm" -> R.drawable.storm_thunder_heavy_rain_cloud
                "Fog" -> R.drawable.cloud_fog
                else -> R.drawable.sun_summer_sunny
            }
        )*/
    }

    fun updateData(){
        //Aviso cuando la info cambie
        notifyDataSetChanged()
    }

    inner class ViewHolder(val itemWeather: WeatherListItemBinding): RecyclerView.ViewHolder(itemWeather.root){
        //val image: ImageView = itemView.findViewById(R.id.imageIcon)
    }
}