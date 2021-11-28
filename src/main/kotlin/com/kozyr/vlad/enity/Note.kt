package com.kozyr.vlad.enity

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val title: Column<String> = varchar("email", 100)
    val description: Column<String> = varchar("description", 500)

    override val primaryKey = PrimaryKey(id)
}

internal fun ResultRow.toNote() = Note(
    id = get(Notes.id),
    title = get(Notes.title),
    description = get(Notes.description)
)

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val description: String,
)

@Serializable
data class NoteDTO(
    val title: String,
    val description: String
)