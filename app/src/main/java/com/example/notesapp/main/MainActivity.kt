package com.example.notesapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.addnote.AddNoteFragment
import com.example.notesapp.notes.NotesAdapter
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddNoteFragment.OnNoteInsertedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter = NotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.notes.observe(this) { notes ->
            adapter.updateData(notes)
        }

        binding.btnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }

        adapter.setOnItemClickListener { note ->
            viewModel.delete(note)
        }

        adapter.setOnPinClickListener { note ->
            viewModel.updateNote(note)
        }
    }

    override fun onNoteInserted() {
        viewModel.getAll()
    }
}