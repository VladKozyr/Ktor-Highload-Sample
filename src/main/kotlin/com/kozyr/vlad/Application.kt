package com.kozyr.vlad

import com.kozyr.vlad.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.kozyr.vlad.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureSerialization()
        DatabaseFactory.init()
    }.start(wait = true)
}
