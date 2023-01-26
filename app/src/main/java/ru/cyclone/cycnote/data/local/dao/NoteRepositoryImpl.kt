package ru.cyclone.cycnote.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.jetbrains.annotations.NotNull
import ru.cyclone.cycnote.domain.model.Note

@Dao
interface NoteRepositoryImpl {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)


    @Query("SELECT * FROM note WHERE id=:noteId")
    suspend fun getNoteById(noteId: Long): Note
}