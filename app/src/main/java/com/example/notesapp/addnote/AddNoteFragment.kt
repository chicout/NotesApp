package com.example.notesapp.addnote

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for activity AddNoteFragment must not be null")
    private val viewModel: AddNoteViewModel by viewModels()
    private var onNoteInsertedListener: OnNoteInsertedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNoteInsertedListener) {
            onNoteInsertedListener = context
        } else {
            throw RuntimeException("$context must implement OnNoteInsertedListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            viewModel.insert(
                Note(
                    id = 0,
                    label = binding.labelInput.text.toString(),
                    content = binding.contentInput.text.toString()
                )
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onNoteInsertedListener?.onNoteInserted()
    }
    interface OnNoteInsertedListener {
        fun onNoteInserted()
    }
}