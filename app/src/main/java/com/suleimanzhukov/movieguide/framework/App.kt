package com.suleimanzhukov.movieguide.framework

import android.app.Application
import android.content.Context
import com.suleimanzhukov.movieguide.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}