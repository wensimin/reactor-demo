package com.github.wensimin.mvcdemo

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/")
class DataController(private val dataRepository: DataRepository, private val restTemplate: RestTemplate) {
    @GetMapping
    fun getNoFlux(): List<Data> {
        val query = restTemplate.getForObject("http://127.0.0.1:8080/query", String::class.java)!!
        return dataRepository.findByData(query)
    }

    @GetMapping("query")
    fun queryString(): String {
        return "updated"
    }

    @EventListener(ContextRefreshedEvent::class)
    fun init() {
        val updateData = Data("beforeUpdate")
        dataRepository.saveAll(
            listOf(
                Data("data1"),
                Data("data1"),
                Data("data1"),
                Data("data1"),
                Data("data1"),
                updateData
            )
        )
        dataRepository.save(Data("updated", updateData.id))
    }

}