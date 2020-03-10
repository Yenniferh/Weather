package com.example.weather


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weather.data.City
import com.example.weather.databinding.FragmentCitiesBinding

/**
 * A simple [Fragment] subclass.
 */
class Cities : Fragment() {

    var citiesList = mutableListOf<City>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCitiesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cities, container, false
        )
        addCities()
        /*binding.CitiesList.setOnClickListener{
            Log.d("List","Clickeaste")
        }*/
        val adapter = CityAdapter()
        binding.CitiesList.adapter = adapter
        adapter.data = citiesList
        return binding.root
    }

    /**
     * "Sunny" -> R.drawable.sun_summer_sunny
    "Hot" -> R.drawable.summer_hot_sun_umbrella
    "Cloud" -> R.drawable.sun_sunny_cloud
    "Rain" -> R.drawable.rain_rainy_heavy_cloud
    "ThunderStorm" -> R.drawable.storm_thunder_heavy_rain_cloud
    "Fog" -> R.drawable.cloud_fog
     */
    fun addCities() {
        citiesList.add(City("Barranquilla", "Sunny"))
        citiesList.add(City("Malambo", "Hot"))
        citiesList.add(City("Bogotá", "Rain"))
        citiesList.add(City("Manizales", "ThunderStorm"))
        citiesList.add(City("Santa Marta", "Fog"))
        citiesList.add(City("Soledad", "Cloud"))
        citiesList.add(City("Montería", "Hot"))
        citiesList.add(City("Puerto Carreño", "Rain"))
        citiesList.add(City("Buenaventura", "ThunderStorm"))
    }
}
