package maciejheintze.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    var feelsLike: Double = 0.0,
    var humidity: Int = 0,
    var pressure: Int = 0,
    var temp: Double = 0.0,
    @SerializedName("temp_max")
    var tempMax: Double = 0.0,
    @SerializedName("temp_min")
    var tempMin: Double = 0.0
)