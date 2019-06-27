package com.example.movies_mvi.presentation

import android.app.Application
import timber.log.Timber

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}