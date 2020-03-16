package com.example.weather


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvapp.CiudadViewModel
import com.example.weather.data.City
import com.example.weather.data.Ciudad
import com.example.weather.data.Forecast
import com.example.weather.databinding.FragmentWeatherBinding
import kotlinx.android.synthetic.main.fragment_cities.view.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class Weather : Fragment() {

    val weather = mutableListOf<Forecast>()
    lateinit var city: City
    var citiesList = mutableListOf<Ciudad>()
    lateinit var navController: NavController
    private var adapter : WeatherAdapter? = null
    private lateinit var viewModel: CiudadViewModel
    lateinit var binder : FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = LayoutInflater.from(this.context).inflate(R.layout.fragment_weather, container, false)
        binder= DataBindingUtil.bind(view)!!
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
        city = arguments!!.getParcelable("city")!!
        binder.city = city
        Log.d("Hello from the other side", city.cityName)
        navController = Navigation.findNavController(view)
        getData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getData() {
        viewModel.getCities().observe(
            viewLifecycleOwner, Observer { obsU ->
                run {
                    citiesList = obsU as MutableList<Ciudad>
                    //Se le asignan los valores de la api a los objetos que usamos para mostrar en la view
                    //acá se obtienen los valores pertinentes y se ignoran los que no necesitamos
                    if(weather.isNotEmpty()) weather.clear() //Para que no se dupliquen las ciudades
                    for (city in citiesList) {

                        if(city.city.name === this.city.cityName){ //Si la ciudad es la misma que se envió como item

                            for (i in 0..city.list.size-1 step 8){
                                val loo=city.list[i]
                                for( wea in loo.weather){ // Se agregan los pronósticos
                                    val main: String = wea.main
                                    val temp:Forecast
                                    val description: String = wea.description
                                    val date:String = loo.dt_txt.toString()
                                    when(i){
                                        0-> {temp = Forecast(main, description,"Today")
                                            weather.add(temp)}

                                        8-> {temp = Forecast(main, description,"Tomorrow")
                                            weather.add(temp)}
                                        else ->{temp = Forecast(main, description, dateTransform(date))
                                            weather.add(temp)}
                                    }

                                    Log.d("forecast",description)


                                }

                            }
                        }

                    }
                    adapter!!.updateData()
                }
            }
        )
    }

}