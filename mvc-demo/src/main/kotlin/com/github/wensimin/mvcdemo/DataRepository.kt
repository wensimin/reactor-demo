package com.github.wensimin.mvcdemo

import org.springframework.data.jpa.repository.JpaRepository

interface DataRepository : JpaRepository<Data, String> {

    fun findByData(data: String): List<Data>

}