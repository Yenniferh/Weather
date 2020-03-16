package com.example.weather

import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("imageResource")
fun getImage(currentWeather: String):Int{
    return when (currentWeather) {
        "clear sky" -> R.drawable.ic_sunny
        "overcast clouds" -> R.drawable.ic_clouds
        "scattered clouds" -> R.drawable.ic_clouds
        "moderate rain" -> R.drawable.ic_moderate_rain
        "few clouds" -> R.drawable.ic_clouds
        "broken clouds" -> R.drawable.ic_clouds
        "light rain" -> R.drawable.ic_light_rain
        "ThunderStorm" -> R.drawable.ic_storm
        "Fog" -> R.drawable.ic_fog
        else -> R.drawable.ic_sunny
    }
}

fun textTransform(text: String):String{
    return text[0].toUpperCase()+text.substring(1)
}

fun dateTransform(dateStamp: String) : String{

    val dateFormat_yyyyMMddHHmmss = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
    )
    val date = dateFormat_yyyyMMddHHmmss.parse(dateStamp)
    val calendar = Calendar.getInstance()
    calendar.setTime(date)

    val dayOfWeekString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)
    return dayOfWeekString
}