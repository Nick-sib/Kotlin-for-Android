package com.nickolay.kotlin_for_android.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.entity.User
import com.nickolay.kotlin_for_android.data.errors.NoAuthException
import com.nickolay.kotlin_for_android.data.model.NoteResult


class FirestoreProvider(val firebaseAuth: FirebaseAuth, val store: FirebaseFirestore) : DataProvider {

     private val currentUser
        get() = firebaseAuth.currentUser

    
    private val notesReference
            get() = currentUser ?.let {
                store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
            } ?: throw NoAuthException()


    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser ?.let {
            User(it.displayName ?: "", it.email ?: "")
        }
    }

    override fun subscribeToAllNotes() : LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            notesReference.addSnapshotListener{ snapshot, e ->
                e?.let {
                    
                } ?: snapshot?.let {
                    val notes = snapshot.documents.mapNotNull {it.toObject(Note::class.java) }
                    value = NoteResult.Success(notes)
                }
            }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

    override fun saveNote(note: Note) : LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            notesReference.document(note.id).set(note)
                .addOnSuccessListener {
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

    override fun getNoteByID(id: String) : LiveData<NoteResult> =  MutableLiveData<NoteResult>().apply {
        try {
            notesReference.document(id).get()
                .addOnSuccessListener {
                    val note = it.toObject(Note::class.java)
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
        } catch (t: Throwable) {
            value = NoteResult.Error(t)
        }
    }

    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }
}
