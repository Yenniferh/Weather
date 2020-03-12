package com.example.weather


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.data.City
import com.example.weather.databinding.FragmentCitiesBinding
import kotlinx.android.synthetic.main.fragment_cities.view.*

/**
 * A simple [Fragment] subclass.
 */
class Cities : Fragment(), CityAdapter.onListClick {

    var citiesList = mutableListOf<City>()
    lateinit var navController: NavController
    private var adapter : CityAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cities,container,false)
        if (citiesList.isEmpty()){
            addCities()
        }
        adapter = CityAdapter(citiesList, this)
        view.CitiesList.layoutManager = LinearLayoutManager(context)
        view.CitiesList.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCardInteraction(city: City?) {
        navController!!.navigate(R.id.action_cities_to_weather)
    }

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
