package ru.cyclone.cycnote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cyclone.cycnote.data.local.dao.NoteRepositoryImpl
import ru.cyclone.cycnote.data.local.dao.PreferencesRepositoryImpl
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.model.ScrollState


@Database(
    entities = [Note::class, ScrollState::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteRepositoryImpl
    abstract fun preferencesDao() : PreferencesRepositoryImpl
}