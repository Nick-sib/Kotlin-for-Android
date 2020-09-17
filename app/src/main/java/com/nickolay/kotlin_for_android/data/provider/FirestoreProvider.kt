package com.nickolay.kotlin_for_android.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult


class FirestoreProvider : DataProvider {

    private val store = FirebaseFirestore.getInstance()
    private val notesReference = store.collection(NOTES_COLLECTION)

    override fun subscribeToAllNotes() : LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.addSnapshotListener{ snapshot, e ->
            e?.let {
                result.value = NoteResult.Error(it)
            } ?: snapshot?.let{
                val notes = snapshot.documents.mapNotNull {it.toObject(Note::class.java)}
                result.value = NoteResult.Success(notes)
            }
        }

        return result
    }

    override fun saveNote(note: Note) : LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(note.id).set(note)
            .addOnSuccessListener {
                result.value = NoteResult.Success(note)
            }.addOnFailureListener {
                result.value = NoteResult.Error(it)
            }
        Log.d("myLOG", "saveNote: ")
        return result
    }

    override fun getNoteByID(id: String) : LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(id).get()
            .addOnSuccessListener {
                result.value = NoteResult.Success(
                    it.toObject(Note::class.java)
                )
            }.addOnFailureListener {
                result.value = NoteResult.Error(it)
            }

        return result
    }

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

}
