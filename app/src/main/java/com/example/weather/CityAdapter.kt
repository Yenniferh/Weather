package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.City
import com.example.weather.databinding.CitiesListItemBinding

class CityAdapter(private val data : List<City>, private val listener: onListClick) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // se hace el data binding
        val binder : CitiesListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.cities_list_item,parent,false )
        return ViewHolder(binder)
    }

    //cantidad de datos
    override fun getItemCount() = data.size

    // se vincula la vista con los datos de la lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemCity.city = item
        holder.itemCity.executePendingBindings()
        holder.itemCity.cityCard.setOnClickListener{
            listener?.onCardInteraction(item)
        }
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
    fun updateData(){
        //Aviso cuando la info cambie
        notifyDataSetChanged()
    }

    inner class ViewHolder(val itemCity: CitiesListItemBinding) : RecyclerView.ViewHolder(itemCity.root) {
        val image: ImageView = itemView.findViewById(R.id.imageIcon)
    }

    interface onListClick{
        fun onCardInteraction(city: City?)
    }
}