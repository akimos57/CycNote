package ru.cyclone.cycnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteID: Long = 0,
    val title: String,
    val content: String,
    val backgroundColor: Int,  // Background color not used yet
    var isFavourite : Boolean
)
