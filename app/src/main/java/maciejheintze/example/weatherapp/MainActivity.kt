package maciejheintze.example.weatherapp

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import maciejheintze.example.weatherapp.Constants.Companion.CITY_NAME_ID
import maciejheintze.example.weatherapp.Constants.Companion.COUNTRY_ID
import maciejheintze.example.weatherapp.Constants.Companion.FEELS_LIKE_ID
import maciejheintze.example.weatherapp.Constants.Companion.ICON_ID
import maciejheintze.example.weatherapp.Constants.Companion.METRIC_ID
import maciejheintze.example.weatherapp.Constants.Companion.PRESSURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMPERATURE_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MAX_ID
import maciejheintze.example.weatherapp.Constants.Companion.TEMP_MIN_ID
import maciejheintze.example.weatherapp.Constants.Companion.WEATHER_DESCRIPTION_ID
import maciejheintze.example.weatherapp.Constants.Companion.WEATHER_ID
import maciejheintze.example.weatherapp.retrofit.MainViewModel
import maciejheintze.example.weatherapp.retrofit.MainViewModelFactory
import maciejheintze.example.weatherapp.retrofit.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    val apiKey : String by lazy{ resources.getString(R.string.api_key) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInput()
    }

    private fun getInput(){
        search_button_id.setOnClickListener {
            if(!search_edit_text_id.text.isNullOrEmpty()){
                val searchString = search_edit_text_id.text.toString()
                fetchData(searchString)
            }
        }
    }

    private fun fetchData(value: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getWeather(value, apiKey, METRIC_ID)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                val country = response.body()?.sys?.country.toString()
                val cityName = response.body()?.name.toString()
                val weather = response.body()?.weather?.get(0)?.main.toString()
                val temperature = response.body()?.main?.temp.toString()
                val feels = response.body()?.main?.feelsLike.toString()
                val temp_max = response.body()?.main?.tempMax.toString()
                val temp_min = response.body()?.main?.tempMin.toString()
                val pressure = response.body()?.main?.pressure.toString()
                val icon = response.body()?.weather?.get(0)?.icon.toString()

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(COUNTRY_ID, country)
                intent.putExtra(CITY_NAME_ID, cityName)
                intent.putExtra(WEATHER_ID, weather)
                intent.putExtra(TEMPERATURE_ID, temperature)
                intent.putExtra(FEELS_LIKE_ID, feels)
                intent.putExtra(TEMP_MAX_ID, temp_max)
                intent.putExtra(TEMP_MIN_ID, temp_min)
                intent.putExtra(PRESSURE_ID, pressure)
                intent.putExtra(ICON_ID, icon)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Wrong input! No data found", Toast.LENGTH_LONG).show()
            }
        })
    }
}