package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.City

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    var data = listOf<City>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cities_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.city.text = item.cityName
        holder.weather.text = item.currentWeather
        holder.image.setImageResource(
            when(item.currentWeather){
                "Sunny" -> R.drawable.sun_summer_sunny
                "Hot" -> R.drawable.summer_hot_sun_umbrella
                "Cloud" -> R.drawable.sun_sunny_cloud
                "Rain" -> R.drawable.rain_rainy_heavy_cloud
                "ThunderStorm" -> R.drawable.storm_thunder_heavy_rain_cloud
                "Fog" -> R.drawable.cloud_fog
                else -> R.drawable.sun_summer_sunny
            }
        )
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val city: TextView = itemView.findViewById(R.id.city_TV)
        val weather: TextView = itemView.findViewById(R.id.weather_TV)
        val image: ImageView = itemView.findViewById(R.id.imageIcon)
    }
}