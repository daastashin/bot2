package com.example.bot2

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiRequester {
    val client: HttpClient = HttpClient.newHttpClient()
    val json = JsonOperator()
    var temp: String = ""

    fun showWeather(city: String): String {
        val city = generateCity(city)
        setWeather(city)
        var resultString: String = "${city.name}: ${city.weather.toString()}" //"lat ${city.lat} lon ${city.lon} city${city.name} weather ${city.weather?.temp}"
        resultString = resultString.replace(".","\\.")
        resultString = resultString.replace("-","\\-")
        resultString = resultString.replace("{","\\{")
        resultString = resultString.replace("}","\\}")
        return resultString
    }

    private fun generateCity(city: String): City {
        val request: HttpRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("https://api.openweathermap.org/geo/1.0/direct?q=$city&limit=6&appid=1596892d1563e1e227902f4bf975843b"))
                .build()
        val httpResponse: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        val city = json.getCity(httpResponse.body())
        return city
    }

    private fun setWeather(city: City) {
        val request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=${city.lat}&lon=${city.lon}&appid=1596892d1563e1e227902f4bf975843b&units=metric"))
                .build()
        val httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString())
        val weather = json.getWeather(httpResponse.body())
        temp = httpResponse.body()
        city.weather = weather
    }
}