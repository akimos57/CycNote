package ru.cyclone.cycnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScrollState(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val state: Int
)
