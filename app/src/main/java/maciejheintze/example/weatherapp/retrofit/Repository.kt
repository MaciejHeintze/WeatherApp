package maciejheintze.example.weatherapp.retrofit

import maciejheintze.example.weatherapp.model.CurrentWeather
import retrofit2.Response

class Repository {

    suspend fun getWeather(name: String, apiKey: String, metric: String) : Response<CurrentWeather> {
        return RetrofitInstance.api.getCurrentWeather(name, apiKey, metric)
    }
}