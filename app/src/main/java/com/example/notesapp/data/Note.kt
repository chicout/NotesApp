package com.example.notesapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val label: String,
    val content: String,
    val isBookmarked: Boolean = false
)
