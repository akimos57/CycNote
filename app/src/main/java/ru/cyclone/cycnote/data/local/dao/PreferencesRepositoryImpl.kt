package ru.cyclone.cycnote.data.local.dao

import androidx.room.*
import ru.cyclone.cycnote.domain.model.ScrollState


/*
* That repository was created to add application preferences
 */
@Dao
interface PreferencesRepositoryImpl {
    @Query("SELECT * FROM ScrollState")
    suspend fun getScrollState() : List<ScrollState>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeScrollState(state : ScrollState)
}