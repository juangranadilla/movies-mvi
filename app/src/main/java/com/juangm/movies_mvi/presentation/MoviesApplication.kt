package com.juangm.movies_mvi.presentation

import android.app.Application
import com.juangm.movies_mvi.presentation.di.dataModule
import com.juangm.movies_mvi.presentation.di.domainModule
import com.juangm.movies_mvi.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@MoviesApplication)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}