package com.example.bot2

import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

//@Service
class MyBot(token: String): TelegramLongPollingBot(token) {

    val apiReq = ApiRequester()

    override fun getBotUsername(): String {
        return "DaniilAstashinBot"
    }

    override fun getBotToken(): String {
        return "6253483668:AAF4kCAkF-YUJ2klhbDV7vQa9b7ki4vH2e4"
    }

    override fun onUpdateReceived(p0: Update) {
        var responseText: String = ""
        if (p0.hasMessage()) {
            val message = p0.message
            val chatId = message.chatId
            if (message.text == "/start") {
                responseText = "Напишите город на английском"
            }
            if (message.text != "/start") {
                responseText = apiReq.showWeather(message.text)
            }
            println(responseText)
            sendNotification(chatId, responseText)
        }
    }
    private fun sendNotification(chatId: Long, responseText: String) {
        val resoponseMessage = SendMessage(chatId.toString(), responseText)
        resoponseMessage.enableMarkdownV2(true)// для жироного текста
                /*       resoponseMessage.enableMarkdown(true)
                       //добавим 4 кнопки
                       resoponseMessage.replyMarkup = getReplyMarkup(
                           listOf(
                               listOf("Кн1","Кн2"),
                               listOf("Кн3","Кн4")
                           )
                       )*/
        execute(resoponseMessage)
    }
    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }
}