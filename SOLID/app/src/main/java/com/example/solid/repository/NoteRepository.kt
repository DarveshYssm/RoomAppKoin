package com.example.solid.repository

import androidx.lifecycle.LiveData
import com.example.solid.data.Note

interface NoteRepository {
    suspend fun insert(note: Note)
    fun getAllNotes(): LiveData<List<Note>>
}
