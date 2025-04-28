package com.example.solid.usecases


import com.example.solid.data.Note
import com.example.solid.repository.NoteRepository

class InsertNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.insert(note)
    }
}
