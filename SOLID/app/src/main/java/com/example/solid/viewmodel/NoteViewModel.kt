package com.example.solid.viewmodel

import com.example.solid.repository.NoteRepository
import kotlinx.coroutines.launch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solid.data.Note
import com.example.solid.usecases.GetAllNotesUseCase
import com.example.solid.usecases.InsertNoteUseCase
import kotlinx.coroutines.launch

class NoteViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    val notes = getAllNotesUseCase()

    fun insert(note: Note) {
        viewModelScope.launch {
            insertNoteUseCase(note)
        }
    }
}
