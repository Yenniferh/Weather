package com.example.rvapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.Ciudad

class CiudadViewModel constructor(application: Application) : AndroidViewModel(application) {


    private var cityDao: CityDao

    init {
        cityDao = CityDao.getInstance(this.getApplication())
    }

    fun addCities() {
        cityDao.addCities()
    }

    fun getCities(): MutableLiveData<List<Ciudad>> {
        return cityDao.getCities()
    }
}