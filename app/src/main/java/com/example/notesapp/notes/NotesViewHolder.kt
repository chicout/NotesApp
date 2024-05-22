package com.example.notesapp.notes

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.ItemNoteBinding

class NotesViewHolder(val binding: ItemNoteBinding, val ctx: Context): RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.apply {
            tvNoteLabel.text = note.label
            tvNoteContent.text = note.content
        }
    }
}