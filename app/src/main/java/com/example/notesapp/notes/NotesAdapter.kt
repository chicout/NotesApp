package com.example.notesapp.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter: RecyclerView.Adapter<NotesViewHolder>()  {

    private var data: List<Note> = emptyList()
    private var onItemClickListener: ((Note) -> Unit)? = null
    private var onPinClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.binding.btnDelete.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        holder.binding.btnBookmark.setOnClickListener {
            onPinClickListener?.invoke(item)
        }

        if (item.isBookmarked) {
            holder.binding.root.background = ContextCompat.getDrawable(holder.ctx,
                R.drawable.shape_rounded_bookmarked_notes
            )
        } else {
            holder.binding.root.background = ContextCompat.getDrawable(holder.ctx,
                R.drawable.shape_rounded_notes
            )
        }
    }

    fun updateData(newData: List<Note>) {
        this.data = newData.sortedByDescending { it.isBookmarked }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        this.onItemClickListener = listener
    }

    fun setOnPinClickListener(listener: (Note) -> Unit) {
        this.onPinClickListener = listener
    }

}
