package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope

abstract class BaseUseCase<T> {
    abstract suspend fun execute(scope: LiveDataScope<T>)
}