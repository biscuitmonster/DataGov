package com.jacob.datagov

import android.app.Application
import com.jacob.datagov.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{modules(appModule)}
    }
}