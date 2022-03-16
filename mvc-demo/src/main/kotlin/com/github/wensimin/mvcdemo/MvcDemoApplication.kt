package com.github.wensimin.mvcdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class MvcDemoApplication {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}

fun main(args: Array<String>) {
    runApplication<MvcDemoApplication>(*args)
}


