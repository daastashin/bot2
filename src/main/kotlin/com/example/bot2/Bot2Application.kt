package com.example.bot2

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun main() {
	//bot
	val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
	val bot = MyBot("6253483668:AAF4kCAkF-YUJ2klhbDV7vQa9b7ki4vH2e4")
	botsApi.registerBot(bot)
	println("is working")
}
