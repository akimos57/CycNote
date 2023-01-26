package ru.cyclone.cycnote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cyclone.cycnote.data.local.dao.NoteRepositoryImpl
import ru.cyclone.cycnote.domain.model.Note


@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteRepositoryImpl
}