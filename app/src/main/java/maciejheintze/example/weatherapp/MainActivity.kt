package maciejheintze.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import maciejheintze.example.weatherapp.retrofit.MainViewModel
import maciejheintze.example.weatherapp.retrofit.MainViewModelFactory
import maciejheintze.example.weatherapp.retrofit.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    val apiKey : String by lazy{ resources.getString(R.string.api_key) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getWeather("Warsaw", apiKey, "metric")
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("test_response", response.body()?.main?.temp.toString())
            }else{
               Log.d("test_response", response.errorBody().toString())
            }
        })
    }
}