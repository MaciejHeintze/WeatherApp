package maciejheintze.example.weatherapp.retrofit

import maciejheintze.example.weatherapp.Constants.Companion.BASE_URL
import maciejheintze.example.weatherapp.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}