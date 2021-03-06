package com.nickolay.kotlin_for_android.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.provider.DataProvider
import com.nickolay.kotlin_for_android.data.provider.FirestoreProvider
import com.nickolay.kotlin_for_android.ui.main.MainViewModel
import com.nickolay.kotlin_for_android.ui.note.NoteViewModel
import com.nickolay.kotlin_for_android.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single<DataProvider> { FirestoreProvider(get(), get()) }
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}