package com.juangm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juangm.domain.action.Action
import com.juangm.domain.result.Result
import com.juangm.presentation.state.ViewState

abstract class BaseViewModel<S: ViewState, A: Action, R: Result> : ViewModel() {
    /**
     * We force every ViewModel to initialize the [internalViewState],
     * and to manage the handle and reduce functions.
     * [internalViewState]: will hold the last view state
     * [handle]: will call the corresponding use case depending on the action
     * [reduce]: will receive the use case result / state, and modify the viewState LiveData
     */
    protected abstract val internalViewState: S
    protected abstract fun handle(action: A): LiveData<R>
    protected abstract fun reduce(result: R): S

    /**
     * LiveData which holds the next action to dispatch
     */
    private val nextAction = MutableLiveData<A>()

    /**
     * LiveData which hold the actual view state.
     * We use [Transformations.switchMap] to apply a function ([handle]) to the [nextAction] LiveData,
     * and [Transformations.map] to apply a function ([reduce]) to the resultant LiveData.
     * So, we ensure that, when an action is dispatched, the nextAction value will change,
     * calling handle first, and then reduce with the result
     */
    val viewState = Transformations.map(Transformations.switchMap(nextAction) {
        handle(it)
    }) {
        reduce(it)
    }

    /**
     * Changes the value of the [nextAction] LiveData with the new action
     */
    fun dispatch(action: A) {
        nextAction.value = action
    }
}