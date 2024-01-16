package com.example.notes_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes_application.db.Note

class NotesAdapter(
    private val listOfNotes:List<Note>,
    private val clickLister: ClickLister
):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(
        itemView:View
    ):ViewHolder(itemView){
        val img:ImageView = itemView.findViewById(R.id.img_delete)
        val heading:TextView = itemView.findViewById(R.id.txt_noteHeading)
        val des:TextView = itemView.findViewById(R.id.txt_noteDes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_note,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = listOfNotes[position]
        holder.heading.text = currentNote.noteName
        holder.des.text = currentNote.noteContext

        holder.itemView.setOnClickListener{

            clickLister.updateNote(currentNote)
        }
        holder.img.setOnClickListener{
            clickLister.delete(currentNote)
        }
    }

    interface ClickLister{
        fun updateNote(note: Note)
        fun delete(note: Note)
    }
}