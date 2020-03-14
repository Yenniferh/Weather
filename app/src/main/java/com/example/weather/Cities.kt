package com.example.weather


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvapp.CiudadViewModel
import com.example.weather.data.City
import com.example.weather.data.Ciudad
import com.example.weather.databinding.FragmentCitiesBinding
import kotlinx.android.synthetic.main.fragment_cities.view.*

/**
 * A simple [Fragment] subclass.
 */
class Cities : Fragment(), CityAdapter.onListClick {

    val cities = mutableListOf<City>()
    var citiesList = mutableListOf<Ciudad>()
    lateinit var navController: NavController
    private var adapter : CityAdapter? = null
    private lateinit var viewModel: CiudadViewModel
    // Random User List


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cities,container,false)
        //Se asigna el viewmodel
        viewModel = ViewModelProvider(this).get(CiudadViewModel::class.java)
       //Se añaden las ciudades
        viewModel.addCities()

       // En caso de ir hacia atrás desde la vista de Weather, no vuelva a cargar la lista de ciudades
       if (citiesList.isEmpty()){
           getData()
       }
        adapter = CityAdapter(cities, this)
        view.CitiesList.layoutManager = LinearLayoutManager(context)
        view.CitiesList.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCardInteraction(city: City?) {
        var bundle = bundleOf("city" to city)
        navController!!.navigate(R.id.action_cities_to_weather, bundle)
        Log.d("Why didn't you pass?", city?.cityName)
    }
    private fun getData() {
        viewModel.getCities().observe(
            viewLifecycleOwner, Observer { obsU ->
                run {
                    citiesList = obsU as MutableList<Ciudad>
                    //Se le asignan los valores de la api a los objetos que usamos para mostrar en la view
                    //acá se obtienen los valores pertinentes y se ignoran los que no necesitamos
                    if(cities.isNotEmpty()) cities.clear() //Para que no se dupliquen las ciudades
                    for (city in citiesList) {
                        var temp = City(
                            city.city.name,//nombre de la ciudad
                            city.list[0].weather[0].description//Descripción del clima actualmente

                        )

                        cities.add(temp)
                    }
                    adapter!!.updateData()
                }
            }
        )
    }

}
