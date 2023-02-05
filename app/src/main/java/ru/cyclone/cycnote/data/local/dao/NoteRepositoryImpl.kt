package ru.cyclone.cycnote.data.local.dao

import androidx.room.*
import ru.cyclone.cycnote.domain.model.Note

@Dao
interface NoteRepositoryImpl {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note " +
            "ORDER BY isFavourite DESC")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id=:noteId")
    suspend fun getNoteById(noteId: Long): Note
}