package com.example.notes_application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes_application.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repo: Repo
):ViewModel() {

    fun getAllData() = repo.getAllData()

    fun insert(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(note)
        }
    }
}