package com.juangm.movies_mvi.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.juangm.movies_mvi.domain.usecase.TopRatedMoviesUseCase

class MoviesViewModel(private val topRatedMoviesUseCase: TopRatedMoviesUseCase): ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}