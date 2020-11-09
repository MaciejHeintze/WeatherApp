package maciejheintze.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
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
        viewModel.getWeather(value, apiKey, "metric")
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("test_response", response.body()?.main?.temp.toString())
            }else{
                Toast.makeText(this, "Wrong input! No data found", Toast.LENGTH_LONG).show()
            }
        })
    }
}