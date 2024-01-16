package com.example.notes_application

import com.example.notes_application.db.DataAccessObject
import com.example.notes_application.db.Note

class Repo(private val dataAccessObject: DataAccessObject) {

    fun insert(note: Note){
        dataAccessObject.insert(note)
    }

    fun delete(note: Note){
        dataAccessObject.delete(note)
    }

    fun update(note: Note){
        dataAccessObject.update(note)
    }

    fun getAllData() = dataAccessObject.getAllData()


}