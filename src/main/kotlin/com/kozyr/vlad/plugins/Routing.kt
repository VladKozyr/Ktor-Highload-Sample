package com.kozyr.vlad.plugins

import com.kozyr.vlad.enity.NoteDTO
import com.kozyr.vlad.service.CacheService
import com.kozyr.vlad.service.NoteService
import com.kozyr.vlad.service.QueueConsumerService
import com.kozyr.vlad.service.QueueProducerService
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import java.nio.charset.StandardCharsets

fun Application.configureRouting() {

    val jedis = Jedis("redis", 6379).apply { ping() }
    val cacheService = CacheService(jedis)

    val queueProducerService = QueueProducerService()
    val queueConsumerService = QueueConsumerService()

    val noteService = NoteService()

    queueConsumerService.consumeMessages("/users/add") {
        noteService.insertNote(Json.decodeFromString(String(it, StandardCharsets.UTF_8)))
    }

    routing {
        get("/users") {
            call.respond(noteService.getAllNotes())
        }

        get("/users/get") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "")

            call.respond(
                cacheService.getByIndex(id.toString()) { noteService.getNoteById(id) }
                    ?: HttpStatusCode.NoContent
            )
        }

        post("/users/add") {

            val note = call.receive<NoteDTO>()

            queueProducerService.sendMessage(
                Json.encodeToString(note).toByteArray(StandardCharsets.UTF_8),
                "/users/add"
            )

            call.respond(HttpStatusCode.Accepted)
        }
    }
}