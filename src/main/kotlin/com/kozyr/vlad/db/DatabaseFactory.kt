package com.kozyr.vlad.db

import com.kozyr.vlad.enity.Notes
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            create(Notes)
            Notes.insertIgnore {
                it[id] = 1
                it[title] = "Buy milk"
                it[description] = "Go to stre and buy some milk"
            }
            Notes.insertIgnore {
                it[id] = 2
                it[title] = "Walk"
                it[description] = "Go for a walk"
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig("/hikari.properties")
        return HikariDataSource(config)
    }
}