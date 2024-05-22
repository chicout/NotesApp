package com.example.notesapp.data

interface DatabaseRepository {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getAll(): List<Note>
    suspend fun updateNote(note: Note)
}