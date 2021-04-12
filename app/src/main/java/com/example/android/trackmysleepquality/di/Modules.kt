package com.example.android.trackmysleepquality.di

import androidx.room.Room
import com.example.android.trackmysleepquality.SleepTrackerApplication
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.sleepdetail.SleepDetailViewModel
import com.example.android.trackmysleepquality.sleepquality.SleepQualityViewModel
import com.example.android.trackmysleepquality.sleeptracker.SleepTrackerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(),
                SleepDatabase::class.java,
                "sleep_history_database")
                .fallbackToDestructiveMigration()
                .build()
    }
    single<SleepDatabaseDao> { get<SleepDatabase>().sleepDatabaseDao }
}

val viewModelModule = module {
    viewModel { (application: SleepTrackerApplication) ->
        SleepTrackerViewModel(get<SleepDatabaseDao>(), application)
    }
    viewModel { (nightKey: Long) -> SleepQualityViewModel(nightKey, get<SleepDatabaseDao>()) }
    viewModel { (nightKey: Long) -> SleepDetailViewModel(nightKey, get<SleepDatabaseDao>()) }
}