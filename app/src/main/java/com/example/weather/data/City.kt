package com.example.weather.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Clase que usaremos para el adapter, Parcelable para poder enviar un objeto a través de Bundle
@Parcelize
data class City(val cityName: String, val currentWeather: String) : Parcelable