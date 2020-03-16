package com.example.weather.data

import com.example.weather.getImage
import java.time.LocalDate
import java.util.*

data class Forecast(val main: String,val description: String,val date: String){
    val image:Int = getImage(description)
}