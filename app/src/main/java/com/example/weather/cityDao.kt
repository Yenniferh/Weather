package com.example.rvapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.weather.data.Ciudad
import com.example.weather.VolleySingleton
import org.json.JSONObject

class CityDao private constructor(var context: Context) {

    private val cities = MutableLiveData<List<Ciudad>>()
    private val cityList = mutableListOf<Ciudad>()
    //Ids de las ciudades que vamos a conseguir
    private val ids= listOf("3689147","5095808","6355945","3687925","3688465","3687238","3668605")
    private var queue: RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }
//Respectivo singleton de nuestro dataObject
    companion object {
        @Volatile
        private var INSTANCE: CityDao? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: CityDao(context).also {
                    INSTANCE = it
                }
            }
    }

    fun addCities() {
        //Pido a la api los datos de las ciudades cuyas ID se encuentran en la listas de ids
        for (i in 0..ids.size-1) {
            VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject(ids[i]))
            }
    }

    fun getCities() = cities

    fun getJsonObject(id:String): JsonObjectRequest {
        //recibe el id de la ciudad y le pregunta a la api por el pronostico para los proximos 5 días con intervalos de 3 horas

        val url = "https://api.openweathermap.org/data/2.5/forecast?id="+id+"&appid=273c64aa172eedf959912d23e6f473f6"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                parseObject(response)
            },
            Response.ErrorListener { error ->
                Log.d("WebRequestTest", "That didn't work ${error.message}")
            }
        )
    }
//Le asigno el los valores de la api al objeto y lo meto en la lista de cityDAO que es la que el livedata observa
    private fun parseObject(response: JSONObject) {

        var ciudad = Ciudad.getCiudad(response)



            cityList.add(ciudad)



        cities.value = cityList
    }

}