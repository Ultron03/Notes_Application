package com.example.notes_application.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    
    @Delete
    fun delete(note: Note)
    
    @Update
    fun update(note: Note)
    
    @Query("Select * from notesTable")
    fun getAllData():LiveData<List<Note>>
}