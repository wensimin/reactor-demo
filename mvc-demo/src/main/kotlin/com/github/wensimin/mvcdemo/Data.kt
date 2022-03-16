package com.github.wensimin.mvcdemo


import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Data(
    val data: String,
    @Id
    @GeneratedValue
    var id: UUID? = null
)