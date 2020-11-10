package maciejheintze.example.weatherapp.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import maciejheintze.example.weatherapp.model.CurrentWeather
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CurrentWeather>> = MutableLiveData()

    fun getWeather(name: String, apiKey: String, metric: String){
        viewModelScope.launch {
            val response = repository.getWeather(name,apiKey,metric)
            myResponse.value = response
        }
    }

    fun getWeatherByGeoCoordinates(lat: String, lon: String, apiKey: String, metric: String){
        viewModelScope.launch {
            val response = repository.getWeatherByGeoCoordinates(lat, lon, apiKey, metric)
            myResponse.value = response
        }
    }
}