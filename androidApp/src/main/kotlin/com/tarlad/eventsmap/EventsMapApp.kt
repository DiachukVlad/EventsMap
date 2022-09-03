package com.tarlad.eventsmap

import android.app.Application
import com.tarlad.eventsmap.di.appModule
import com.tarlad.eventsmap.shared.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EventsMapApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EventsMapApp)
            modules(
                appModule,
                sharedModule
            )
        }
    }
}