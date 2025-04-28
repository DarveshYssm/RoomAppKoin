package com.example.solid.di

import com.example.solid.usecases.GetAllNotesUseCase
import com.example.solid.usecases.InsertNoteUseCase


import androidx.room.Room
import com.example.solid.data.AppDatabase
import com.example.solid.data.MIGRATION_1_2
import com.example.solid.repository.NoteRepository
import com.example.solid.repository.NoteRepositoryImpl
import com.example.solid.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single { get<AppDatabase>().noteDao() }

    single<NoteRepository> { NoteRepositoryImpl(get()) }

    factory { InsertNoteUseCase(get()) }
    factory { GetAllNotesUseCase(get()) }

    viewModel { NoteViewModel(get(), get()) }
}
