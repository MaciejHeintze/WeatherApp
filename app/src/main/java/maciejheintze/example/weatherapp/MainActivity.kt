package maciejheintze.example.weatherapp

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.widget.NumberPicker
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
    private lateinit var country : String
    private lateinit var cityName : String
    private lateinit var weather : String
    private lateinit var temperature : String
    private lateinit var feels : String
    private lateinit var temp_max : String
    private lateinit var temp_min : String
    private lateinit var pressure : String
    private lateinit var icon : String
    private var units_id = "metric"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInput()
        getUnitsPickerValue()
    }

    private fun getInput(){
        search_button_id.setOnClickListener {
            if(!search_edit_text_id.text.isNullOrEmpty()){
                val searchString = search_edit_text_id.text.toString()
                getUnitsPickerValue()
                fetchData(searchString)
            }
        }
        search_by_geo_coordinates_button.setOnClickListener {
            if(!lat_search_edit_text_id.text.isNullOrEmpty() || !lon_search_edit_text_id.text.isNullOrEmpty()){
                getUnitsPickerValue()
                val lat = lat_search_edit_text_id.text.toString()
                val lon = lon_search_edit_text_id.text.toString()
                searchByGeoCoordinates(lat, lon)
            }
        }
    }

    private fun getUnitsPickerValue(){
        val picker = findViewById<NumberPicker>(R.id.units_picker)
        val units = arrayOf("metric", "standard", "imperial")
        picker.minValue = 0
        picker.maxValue = units.size - 1
        picker.displayedValues = units
        picker.wrapSelectorWheel = true


        picker.setOnValueChangedListener { _, _, value ->
            units_id = units[value]
        }
    }

    private fun fetchData(value: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getWeather(value, apiKey, units_id)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                country = response.body()?.sys?.country.toString()
                cityName = response.body()?.name.toString()
                weather = response.body()?.weather?.get(0)?.main.toString()
                temperature = response.body()?.main?.temp.toString()
                feels = response.body()?.main?.feelsLike.toString()
                temp_max = response.body()?.main?.tempMax.toString()
                temp_min = response.body()?.main?.tempMin.toString()
                pressure = response.body()?.main?.pressure.toString()
                icon = response.body()?.weather?.get(0)?.icon.toString()
                sendData()
            }else{
                Toast.makeText(this, "Wrong input! No data found", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun searchByGeoCoordinates(lat: String, lon: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getWeatherByGeoCoordinates(lat, lon, apiKey, units_id)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                country = response.body()?.sys?.country.toString()
                cityName = response.body()?.name.toString()
                weather = response.body()?.weather?.get(0)?.main.toString()
                temperature = response.body()?.main?.temp.toString()
                feels = response.body()?.main?.feelsLike.toString()
                temp_max = response.body()?.main?.tempMax.toString()
                temp_min = response.body()?.main?.tempMin.toString()
                pressure = response.body()?.main?.pressure.toString()
                icon = response.body()?.weather?.get(0)?.icon.toString()
                sendData()
            }else{
                Toast.makeText(this, "Wrong input! No data found", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun sendData(){
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
    }
}