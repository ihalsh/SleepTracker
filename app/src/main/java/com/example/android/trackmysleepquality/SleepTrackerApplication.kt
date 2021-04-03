package com.example.android.trackmysleepquality

import android.app.Application
import com.example.android.trackmysleepquality.di.appModule
import com.example.android.trackmysleepquality.di.dbModule
import com.example.android.trackmysleepquality.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SleepTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SleepTrackerApplication)
            modules(listOf(dbModule, appModule, viewModelModule))
        }
    }
}