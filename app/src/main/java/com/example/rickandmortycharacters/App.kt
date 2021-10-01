package com.example.rickandmortycharacters

import android.app.Application
import com.example.rickandmortycharacters.DI.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(KoinModules.netModule, KoinModules.MvvmModule, KoinModules.pagingModule)
        }
    }
}
