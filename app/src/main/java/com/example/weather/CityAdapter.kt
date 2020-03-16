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
        /*holder.image.setImageResource(
            when (item.currentWeather) {
                "clear sky" -> R.drawable.ic_sunny
                "overcast clouds" -> R.drawable.ic_clouds
                "scattered clouds" -> R.drawable.ic_clouds
                "few clouds" -> R.drawable.ic_clouds
                "broken clouds" -> R.drawable.ic_clouds
                "light rain" -> R.drawable.ic_light_rain
                "ThunderStorm" -> R.drawable.ic_storm
                "Fog" -> R.drawable.ic_fog
                else -> R.drawable.ic_rain
            }
        )*/
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