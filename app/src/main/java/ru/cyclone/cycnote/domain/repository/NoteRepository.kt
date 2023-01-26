package ru.cyclone.cycnote.domain.repository

import ru.cyclone.cycnote.data.local.dao.NoteRepositoryImpl
import ru.cyclone.cycnote.domain.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteRepositoryImpl: NoteRepositoryImpl) {

    suspend fun getAllNotes(): List<Note> = noteRepositoryImpl.getAllNotes()

    suspend fun insertNote(note: Note) = noteRepositoryImpl.insertNote(note = note)

    suspend fun deleteNote(note: Note) = noteRepositoryImpl.deleteNote(note = note)

    suspend fun getNoteById(id: Long) = noteRepositoryImpl.getNoteById(noteId = id)
}