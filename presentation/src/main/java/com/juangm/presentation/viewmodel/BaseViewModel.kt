package com.juangm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juangm.domain.action.Action
import com.juangm.domain.result.Result
import com.juangm.presentation.state.ViewState
import timber.log.Timber

abstract class BaseViewModel<S: ViewState, A: Action, R: Result>(private val initialViewState: S) : ViewModel() {

    /**
     * LiveData which holds the next action to dispatch
     */
    private val nextAction = MutableLiveData<A>()

    /**
     * LiveData which hold the current view state
     */
    private var _viewState: LiveData<S> = MutableLiveData(initialViewState)
    val viewState: LiveData<S>
        get() = _viewState

    /**
     * We use Transformations.switchMap to apply a function (handle) to the nextAction LiveData,
     * and Transformations.map to apply a function (reduce) to the resultant LiveData.
     * So, we ensure that, when an action is dispatched, the nextAction value will change,
     * calling handle first, and then reduce with the result
     */
    init {
        _viewState = Transformations.map(Transformations.switchMap(nextAction) {
            handle(it)
        }) {
            reduce(_viewState.value ?: initialViewState, it)
        }
    }

    /**
     * We force every ViewModel to manage the handle and reduce functions.
     * [handle]: will call the corresponding use case depending on the action
     * [reduce]: will receive the use case result and the last state, and will create the new state
     */
    protected abstract fun handle(action: A): LiveData<R>
    protected abstract fun reduce(currentViewState: S, result: R): S

    /**
     * Changes the value of the [nextAction] LiveData with the new action
     */
    fun dispatch(action: A) {
        Timber.i("Dispatch: $action")
        nextAction.value = action
    }
}