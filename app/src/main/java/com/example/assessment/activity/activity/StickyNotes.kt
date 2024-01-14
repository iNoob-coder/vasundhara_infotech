package com.example.assessment.activity.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assessment.R
import com.example.assessment.activity.Model.Note
import com.example.assessment.activity.adapter.NotesAdapter
import com.example.assessment.databinding.ActivityStickyNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.random.Random

class StickyNotes : AppCompatActivity() {

    private lateinit var binding: ActivityStickyNotesBinding
    private val notes = mutableListOf<Note>()
    private lateinit var noteAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStickyNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NotesAdapter(
            notes,
            { note -> deleteNote(note) },
            { openNoteBottomSheet() }
        )
        binding.recyclerView.apply {
            this.adapter = noteAdapter
            this.layoutManager = GridLayoutManager(this@StickyNotes, 3)
        }
    }

    private fun openNoteBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.notes_dialogbox, null)

        val btnCancel: Button = dialogView.findViewById(R.id.btn_cancel)
        val btnOk: Button = dialogView.findViewById(R.id.btn_save)
        val noteTitle: EditText = dialogView.findViewById(R.id.notes_title)
        val notesEditText: EditText = dialogView.findViewById(R.id.notes)

        btnCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        btnOk.setOnClickListener {
            //add note
            val noteTitleText = noteTitle.text.toString().trim()
            val noteText = notesEditText.text.toString().trim()
            if (noteText.isNotEmpty()) {
                val newNote = Note(notes.size + 1, noteTitleText, noteText, getBackgroundColor())
                notes.add(newNote)
                noteAdapter.notifyDataSetChanged()
            }
            // Do something with the user input
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(dialogView)

        bottomSheetDialog.show()
    }

    private fun deleteNote(note: Note) {
        notes.remove(note)
        noteAdapter.notifyDataSetChanged()
    }

    private fun getBackgroundColor(): Int {
        return Color.rgb(
            Random.nextInt(128, 256),  // Red component (128 to 255)
            Random.nextInt(128, 256),  // Green component (128 to 255)
            Random.nextInt(128, 256)   // Blue component (128 to 255)
        )
    }
}