package com.example.weather


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvapp.CiudadViewModel
import com.example.weather.data.City
import com.example.weather.data.Ciudad
import com.example.weather.data.Forecast
import kotlinx.android.synthetic.main.fragment_cities.view.*
import kotlinx.android.synthetic.main.fragment_weather.view.*

/**
 * A simple [Fragment] subclass.
 */
class Weather : Fragment() {

    val weather = mutableListOf<Forecast>()
    var citiesList = mutableListOf<Ciudad>()
    lateinit var navController: NavController
    private var adapter : WeatherAdapter? = null
    private lateinit var viewModel: CiudadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        //Se asigna el viewmodel
        viewModel = ViewModelProvider(this).get(CiudadViewModel::class.java)

        // En caso de ir hacia atrás desde la vista de Weather, no vuelva a cargar la lista de ciudades
        if (citiesList.isEmpty()){
            getData()
        }
        adapter = WeatherAdapter(weather)
        view.WeatherList.layoutManager = LinearLayoutManager(context)
        view.WeatherList.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun getData() {
        viewModel.getCities().observe(
            viewLifecycleOwner, Observer { obsU ->
                run {
                    citiesList = obsU as MutableList<Ciudad>
                    //Se le asignan los valores de la api a los objetos que usamos para mostrar en la view
                    //acá se obtienen los valores pertinentes y se ignoran los que no necesitamos
                    if(weather.isNotEmpty()) weather.clear() //Para que no se dupliquen las ciudades
                    for (city in citiesList) {
                        for (wea in city.list[0].weather){
                            val main: String = wea.main
                            val description: String = wea.description
                            val temp = Forecast(main, description)
                            weather.add(temp)
                        }
                    }
                    adapter!!.updateData()
                }
            }
        )
    }

}
//Bogotá 5095808