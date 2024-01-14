package com.example.assessment.activity.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.activity.Model.Note
import com.example.assessment.databinding.AddButtonLayoutBinding
import com.example.assessment.databinding.NoteLayoutBinding
import kotlin.random.Random

class NotesAdapter(
    private val noteList: List<Note>,
    private val onNoteLongClickListener: (Note) -> Unit,
    private val onAddButtonClickListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_NOTE = 0
        private const val VIEW_TYPE_ADD_BUTTON = 1
    }

    class NoteViewHolder(val binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    class AddButtonViewHolder(
        val binding: AddButtonLayoutBinding,
        onAddButtonClickListener: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnAdd.setOnClickListener { onAddButtonClickListener() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NOTE -> {
                val binding =
                    NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NoteViewHolder(binding)
            }
            VIEW_TYPE_ADD_BUTTON -> {
                val binding =
                    AddButtonLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AddButtonViewHolder(binding, onAddButtonClickListener)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return noteList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < noteList.size) {
            VIEW_TYPE_NOTE
        } else {
            VIEW_TYPE_ADD_BUTTON
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteViewHolder -> {
                val data = noteList[position]
                holder.binding.note.visibility = View.VISIBLE
                if (data.title.isNotEmpty()) holder.binding.noteTitle.text = data.title
                holder.binding.note.text = data.subject
                holder.itemView.setBackgroundColor(data.bgColor)
                holder.itemView.setOnLongClickListener { onNoteLongClickListener(data); true }
            }
            is AddButtonViewHolder -> {
                holder.binding.btnAdd.visibility = View.VISIBLE
            }
        }
    }
}
