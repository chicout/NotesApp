package com.example.notesapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.DatabaseRepository
import com.example.notesapp.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel() {

    init {
        getAll()
    }

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note = note)
            _notes.postValue(repository.getAll())
        }
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.postValue(repository.getAll())
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(Note(id = note.id, label = note.label, content = note.content, isBookmarked = !note.isBookmarked))
            _notes.postValue(repository.getAll())
        }
    }
}