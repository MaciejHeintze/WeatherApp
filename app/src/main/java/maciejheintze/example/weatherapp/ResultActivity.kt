package maciejheintze.example.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_result.*
import maciejheintze.example.weatherapp.Constants.Companion.CELCIUS_SIGN
import maciejheintze.example.weatherapp.Constants.Companion.CITY_NAME_ID
import maciejheintze.example.weatherapp.Constants.Companion.COUNTRY_ID
import maciejheintze.example.weatherapp.Constants.Companion.FEELS_LIKE_ID
import maciejheintze.example.weatherapp.Constants.Companion.ICON_ID
import maciejheintze.example.weatherapp.Constants.Companion.PRESSURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMPERATURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MAX_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MIN_ID
import maciejheintze.example.weatherapp.Constants.Companion.WEATHER_ID

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        getData()
    }

    private fun getData(){
        val country = intent.getStringExtra(COUNTRY_ID).toString()
        val cityName = intent.getStringExtra(CITY_NAME_ID).toString()
        val weather = intent.getStringExtra(WEATHER_ID).toString()
        val temperature = intent.getStringExtra(TEMPERATURE_ID).toString()
        val feels = intent.getStringExtra(FEELS_LIKE_ID).toString()
        val temp_max = intent.getStringExtra(TEMP_MAX_ID).toString()
        val temp_min = intent.getStringExtra(TEMP_MIN_ID).toString()
        val pressure = intent.getStringExtra(PRESSURE_ID).toString()
        val icon = intent.getStringExtra(ICON_ID).toString()
        displayData(icon, cityName, country, weather, temperature, feels, temp_max, temp_min, pressure)
    }

    @SuppressLint("SetTextI18n")
    private fun displayData(icon: String, cityName: String, country: String, weather: String, temperature: String, feels: String, max: String, min: String, pressure: String){
        val iconUrl = "http://openweathermap.org/img/w/$icon.png"
        Picasso.get().load(iconUrl).into(weather_icon_id)

        city_and_country_text_view_id.text = "$cityName, $country"
        weather_text_view_id.text = weather
        temperature_text_view_id.text = "$temperature$CELCIUS_SIGN"
        real_feel_text_view_id.text = "Real feel: $feels$CELCIUS_SIGN"
        min_max_temp_text_view_id.text = "max: $max$CELCIUS_SIGN/ min: $min$CELCIUS_SIGN"
        pressure_text_view_id.text = "Pressure: $pressure"
    }
}