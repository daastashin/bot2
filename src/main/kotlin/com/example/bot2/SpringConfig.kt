package com.example.bot2

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:properties")
class SpringConfig {

    @Value("\${token}")
    var token: String? = null

    @Value("\${botUserName}")
    var botUserName: String? = null

    @Bean
    fun myBot(): MyBot = MyBot(token, botUserName)

}