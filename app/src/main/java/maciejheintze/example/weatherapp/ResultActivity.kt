package maciejheintze.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import maciejheintze.example.weatherapp.Constants.Companion.CITY_NAME_ID
import maciejheintze.example.weatherapp.Constants.Companion.COUNTRY_ID
import maciejheintze.example.weatherapp.Constants.Companion.FEELS_LIKE_ID
import maciejheintze.example.weatherapp.Constants.Companion.PRESSURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMPERATURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MAX_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MIN_ID
import maciejheintze.example.weatherapp.Constants.Companion.WEATHER_DESCRIPTION_ID
import maciejheintze.example.weatherapp.Constants.Companion.WEATHER_ID

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        getData()
    }

    private fun getData(){
        val country = intent.getStringExtra(COUNTRY_ID)
        val cityName = intent.getStringExtra(CITY_NAME_ID)
        val weather = intent.getStringExtra(WEATHER_ID)
        val weatherDescription = intent.getStringExtra(WEATHER_DESCRIPTION_ID)
        val temperature = intent.getStringExtra(TEMPERATURE_ID)
        val feels = intent.getStringExtra(FEELS_LIKE_ID)
        val temp_max = intent.getStringExtra(TEMP_MAX_ID)
        val temp_min = TEMP_MIN_ID
        val pressure = intent.getStringExtra(PRESSURE_ID)
    }
}