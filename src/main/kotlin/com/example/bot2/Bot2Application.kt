package com.example.bot2

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {

	val context = AnnotationConfigApplicationContext(SpringConfig::class.java)
	val myBot = context.getBean("myBot",MyBot::class.java)

	val botsApi = TelegramBotsApi(DefaultBotSession::class.java)

	botsApi.registerBot(myBot)
	println("is working")
}
