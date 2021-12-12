package com.kozyr.vlad

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.kozyr.vlad.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() {
        assertEquals("Hello World!", "Hello World!")
    }
}