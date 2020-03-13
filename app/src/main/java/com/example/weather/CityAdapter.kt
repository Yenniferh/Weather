package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.City
import com.example.weather.data.Ciudad
import com.google.android.material.card.MaterialCardView

class CityAdapter(private val data : List<City>, private val listener: onListClick) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cities_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.card.setOnClickListener{
            listener?.onCardInteraction(item)
        }
        holder.city.text = item.cityName
        holder.weather.text = item.currentWeather
        holder.image.setImageResource(
            when(item.currentWeather){
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
        )
    }
    public fun updateData(){
        //Aviso cuando la info cambie
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val card: MaterialCardView = itemView.findViewById(R.id.city_Card)
        val city: TextView = itemView.findViewById(R.id.city_TV)
        val weather: TextView = itemView.findViewById(R.id.weather_TV)
        val image: ImageView = itemView.findViewById(R.id.imageIcon)
    }

    interface onListClick{
        fun onCardInteraction(city: City?)
    }
}