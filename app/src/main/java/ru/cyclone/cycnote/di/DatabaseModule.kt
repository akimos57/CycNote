package ru.cyclone.cycnote.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.cyclone.cycnote.data.local.AppDatabase
import ru.cyclone.cycnote.data.local.dao.NoteRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideAppDate(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteRepositoryImpl {
        return appDatabase.noteDao()
    }

}