package com.kozyr.vlad.service

import com.kozyr.vlad.enity.NoteDTO
import com.kozyr.vlad.enity.Notes
import com.kozyr.vlad.enity.toNote
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class NoteService {

    suspend fun getAllNotes() = newSuspendedTransaction {
        Notes.selectAll().map { it.toNote() }
    }

    suspend fun getNoteById(id: Int) = newSuspendedTransaction {
        Notes.select { Notes.id eq id }
            .firstOrNull()
            ?.toNote()
    }

    internal fun insertNote(note: NoteDTO) {
        transaction {
            Notes.insert {
                it[title] = note.title
                it[description] = note.description
            }
        }
    }
}