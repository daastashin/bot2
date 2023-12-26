package com.example.bot2

class Weather(val temp: Int, val pressure: Int, val humidity: Int) {
    override fun toString(): String {
        return "temp: $temp, pressure: $pressure, humidity: $humidity"
    }
}