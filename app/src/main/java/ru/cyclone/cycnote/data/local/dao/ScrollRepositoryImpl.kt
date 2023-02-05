package ru.cyclone.cycnote.data.local.dao

import androidx.room.*
import ru.cyclone.cycnote.domain.model.ScrollState

@Dao
interface ScrollRepositoryImpl {
    @Query("SELECT * FROM ScrollState")
    suspend fun getScrollState() : List<ScrollState>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeScrollState(state : ScrollState)
}