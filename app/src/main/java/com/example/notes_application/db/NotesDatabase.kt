package com.example.notes_application.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase :RoomDatabase(){

    abstract fun getNoteDao():DataAccessObject

    companion object{
        private const val DB_NAME = "note_database.db"

        private var instance: NotesDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: buildDatabase(context = context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            DB_NAME
        ).build()

    }

}