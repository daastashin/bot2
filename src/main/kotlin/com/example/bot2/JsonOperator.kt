package com.example.bot2

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JsonOperator {

    fun getCity(httpResponse: String): City {
        var json = JsonParser.parseString(httpResponse)
        val jsonArray: JsonArray = json.asJsonArray
        json = JsonParser.parseString(jsonArray[0].toString())
        val jsonObject: JsonObject = json.getAsJsonObject()
        val city = City(jsonObject.get("name").asString, jsonObject.get("lat").asFloat,jsonObject.get("lon").asFloat)
        return city
    }

    fun getWeather(httpResponse: String): Weather {
        val json = JsonParser.parseString(httpResponse)
        var jsonObject: JsonObject = json.asJsonObject
        var jsonElement = jsonObject.get("main").asJsonObject
        val weather: Weather = Weather(jsonElement.get("temp").asFloat.toInt(),
                jsonElement.get("pressure").asInt,
                jsonElement.get("humidity").asInt)
        return weather
    }
}