package com.juangm.movies_mvi.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juangm.movies_mvi.domain.action.Action
import com.juangm.movies_mvi.domain.result.Result

abstract class BaseViewModel<S: ViewState, A: Action, R: Result> : ViewModel() {
    protected abstract val internalViewState: S

    protected abstract fun handle(action: A): LiveData<R>
    protected abstract fun reduce(result: R): S

    private val nextAction = MutableLiveData<A>()

    val viewState = Transformations.map(Transformations.switchMap(nextAction) {
        handle(it)
    }) {
        reduce(it)
    }

    fun dispatch(action: A) {
        nextAction.value = action
    }
}