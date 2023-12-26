package com.example.bot2

class City(val name: String, val lat: Float, val lon: Float) {

    var weather: Weather? = null

    override fun toString(): String {
        return "name: $name, lat:$lat, lon:$lon"
    }
}