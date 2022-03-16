package com.github.wensimin.reactordemo

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface DataRepository : ReactiveCrudRepository<Data, String> {

    fun findByData(data: String): Flux<Data>

}