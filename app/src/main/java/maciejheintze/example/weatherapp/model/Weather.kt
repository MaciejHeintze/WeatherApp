package maciejheintze.example.weatherapp.model

data class Weather(
    var description: String = "",
    var icon: String = "",
    var id: Int = 0,
    var main: String = ""
)