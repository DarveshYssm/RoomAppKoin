package com.example.solid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.solid.viewmodel.NoteViewModel
import com.example.solid.data.AppDatabase
import com.example.solid.data.MIGRATION_1_2
import com.example.solid.data.Note
import com.example.solid.databinding.ActivityMainBinding
import com.example.solid.repository.NoteRepositoryImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    private val noteViewModel: NoteViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
            .noteDao()

        noteViewModel.notes.observe(this) { notes ->
            noteAdapter.submitList(notes)
        }

        binding.btnAddNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            if (title.isNotEmpty()) {
                val note = Note(title = title, description = "")
                noteViewModel.insert(note)
                binding.etNoteTitle.text.clear()
            }
        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.rvNotes.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
