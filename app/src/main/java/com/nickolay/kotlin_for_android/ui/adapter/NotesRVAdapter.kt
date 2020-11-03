package com.nickolay.kotlin_for_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*

class NotesRVAdapter(
        val onItemClick: ((Note) -> Unit)? = null
) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(notes[position])

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {
                fun bind(note: Note) {
                    tv_title.text = note.title
                    tv_text.text = note.text
                    itemView
                        .setBackgroundColor(
                                ContextCompat
                                    .getColor(itemView.context, note.color.id))

                    itemView.setOnClickListener {
                        onItemClick?.invoke(note)
                    }
                }
            }

}