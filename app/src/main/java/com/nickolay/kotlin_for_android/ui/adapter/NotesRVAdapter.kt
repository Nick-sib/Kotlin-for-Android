package com.nickolay.kotlin_for_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(note) {
            itemView.tv_title.text = title
            itemView.tv_text.text = text
            itemView.setBackgroundColor(color)
        }
    }

}