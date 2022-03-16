package com.github.wensimin.reactordemo

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import java.util.*


data class Data(
    val data: String,
    private var id: UUID? = null
) : Persistable<UUID> {
    @Id
    override fun getId(): UUID? = id
    override fun isNew(): Boolean {
        return (id == null).also {
            id = if (it) UUID.randomUUID() else id
        }
    }
}