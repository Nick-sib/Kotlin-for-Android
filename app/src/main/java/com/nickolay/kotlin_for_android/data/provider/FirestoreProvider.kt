package com.nickolay.kotlin_for_android.data.provider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.entity.User
import com.nickolay.kotlin_for_android.data.errors.NoAuthException
import com.nickolay.kotlin_for_android.data.model.NoteResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class FirestoreProvider(private val firebaseAuth: FirebaseAuth, private val store: FirebaseFirestore) : DataProvider {

    private val currentUser
        get() = firebaseAuth.currentUser


    private val notesReference
        get() = currentUser?.let {
            store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
        } ?: throw NoAuthException()


    override fun subscribeToAllNotes(): ReceiveChannel<NoteResult> =
        Channel<NoteResult>(CONFLATED).apply {
            var registration: ListenerRegistration? = null
            try {
                registration = notesReference.addSnapshotListener { snapshot, e ->
                    val value = e?.let {
                    NoteResult.Error(it)
                } ?: snapshot?.let {
                    val notes = snapshot.documents.mapNotNull { it.toObject(Note::class.java) }
                    NoteResult.Success(notes)
                }
                    value?.let {
                        offer(it)
                    }
                }
            } catch (t: Throwable) {
                offer(NoteResult.Error(t))
            }
            invokeOnClose {
            registration?.remove()
            }
        }


    override suspend fun getCurrentUser(): User? =
        suspendCoroutine { continuation ->
            continuation.resume(
                currentUser?.let {
                    User(it.displayName ?: "", it.email ?: "")
                }
            )
        }


    override suspend fun saveNote(note: Note): Note =
        suspendCoroutine { continuation ->
            try {
                notesReference
                    .document(note.id)
                    .set(note)
                    .addOnSuccessListener { continuation.resume(note) }
                    .addOnFailureListener { continuation.resumeWithException(it) }
            } catch (t: Throwable) {
                continuation.resumeWithException(t)
            }
        }


    override suspend fun getNoteByID(id: String): Note? =
        suspendCoroutine { continuation ->
            try {
                notesReference
                    .document(id)
                    .get()
                    .addOnSuccessListener {
                            val note = it.toObject(Note::class.java)
                            continuation.resume(note)
                        }
                    .addOnFailureListener {
                            continuation.resumeWithException(it)
                        }
            } catch (t: Throwable) {
                continuation.resumeWithException(t)
            }
        }


    override suspend fun deleteNote(id: String): Unit =
        suspendCoroutine { continuation ->
            try {
                notesReference
                    .document(id)
                    .delete()
                    .addOnSuccessListener { continuation.resume(Unit) }
                    .addOnFailureListener { continuation.resumeWithException(it) }
            } catch (t: Throwable) {
                continuation.resumeWithException(t)
            }
        }


    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }
}
