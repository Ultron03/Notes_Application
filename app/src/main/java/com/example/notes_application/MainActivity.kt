package com.example.notes_application

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_application.db.Note
import com.example.notes_application.db.NotesDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity(), NotesAdapter.ClickLister {
    private lateinit var repo: Repo
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesViewModelFactory: NotesViewModelFactory
    private lateinit var notesDatabase: NotesDatabase
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var rv:RecyclerView
    private lateinit var fab:FloatingActionButton
    private lateinit var  dialog:Dialog
    private lateinit var edtNoteName:EditText
    private lateinit var edtNoteConten:EditText
    private lateinit var btnSave:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        notesViewModel.getAllData().observe(this){
            notesAdapter = NotesAdapter(it,this)
            rv.adapter = notesAdapter
            rv.layoutManager = LinearLayoutManager(this)
            Log.i("ListOfNotes",it.toString())
        }

        fab.setOnClickListener {
            openDialog(true,null)
        }

    }

    private fun openDialog(comingFromFAB:Boolean,prenote: Note?){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.layout_dialog)

        edtNoteName = dialog.findViewById(R.id.edt_noteName)
        edtNoteConten = dialog.findViewById(R.id.edt_noteContent)
        btnSave = dialog.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {

            if(comingFromFAB){
                val note = Note(
                    noteName = edtNoteName.text.toString(),
                    noteContext = edtNoteConten.text.toString()
                )
                notesViewModel.insert(note)
            } else{
                val note = prenote?.id?.let { it1 ->
                    Note(
                        id = it1,
                        noteName = edtNoteName.text.toString(),
                        noteContext = edtNoteConten.text.toString()
                    )
                }
                Log.i("NoteUpdated",note.toString())

                if (note != null) {
                    notesViewModel.update(note)
                }
                Toast.makeText(this,"Added", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun init() {
        notesDatabase = NotesDatabase(this)
        repo = Repo(notesDatabase.getNoteDao())
        notesViewModelFactory = NotesViewModelFactory(repo)
        notesViewModel = ViewModelProvider(this,notesViewModelFactory).get(NotesViewModel::class.java)
        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)
    }

    override fun updateNote(note: Note) {
        openDialog(false,note)


    }

    override fun delete(note: Note) {

        notesViewModel.delete(note)
    }
}