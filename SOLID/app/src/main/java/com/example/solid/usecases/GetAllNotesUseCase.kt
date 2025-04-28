package com.example.solid.usecases


import androidx.lifecycle.LiveData
import com.example.solid.data.Note
import com.example.solid.repository.NoteRepository

class GetAllNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): LiveData<List<Note>> {
        return repository.getAllNotes()
    }
}
