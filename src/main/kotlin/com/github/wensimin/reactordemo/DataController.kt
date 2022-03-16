package com.github.wensimin.reactordemo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/")
class DataController(private val dataRepository: DataRepository, private val webClient: WebClient) {
    @GetMapping
    suspend fun get(): Flow<Data> {
        val query =
            webClient.get().uri("http://127.0.0.1:8080/query").retrieve().awaitBody<String>()
        return dataRepository.findByData(query).asFlow()
    }
    //单个例子
//    suspend fun findOne():Data?{
//        return dataRepository.findById("").awaitFirstOrNull()
//    }

    @GetMapping("noKotlin")
    fun getNoKotlin(): Flux<Data> {
        return webClient.get().uri("http://127.0.0.1:8080/query").retrieve().bodyToMono(String::class.java)
            .flatMapMany {
                dataRepository.findByData(it)
            }
    }


    @GetMapping("query")
    fun queryString(): String {
        return "updated"
    }


    @EventListener(ContextRefreshedEvent::class)
    fun init() {
        val updateData = Data("beforeUpdate")
        val res = dataRepository.saveAll(
            listOf(
                Data("data1"),
                Data("data1"),
                Data("data1"),
                Data("data1"),
                Data("data1"),
                updateData
            )
        ).subscribe()
        res.let {
            println(it)
            dataRepository.save(Data("updated", updateData.id)).subscribe()
        }
    }
}