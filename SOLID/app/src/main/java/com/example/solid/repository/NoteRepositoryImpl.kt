package com.example.solid.repository

import androidx.lifecycle.LiveData
import com.example.solid.data.Note
import com.example.solid.data.NoteDao

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override suspend fun insert(note: Note) = dao.insert(note)
    override fun getAllNotes(): LiveData<List<Note>> = dao.getAllNotes()
}

