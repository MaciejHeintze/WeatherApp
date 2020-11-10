package maciejheintze.example.weatherapp.api

import maciejheintze.example.weatherapp.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") q: String,
                                  @Query("appid") appid: String,
                                  @Query("units") units: String) : Response<CurrentWeather>

    @GET("weather")
    suspend fun getWeatherByGeoCoordinates(@Query("lat") lat: String,
                                           @Query("lon") lon: String,
                                           @Query("appid") appid: String,
                                           @Query("units") units: String) : Response<CurrentWeather>
}