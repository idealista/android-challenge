package com.idealista.android.challenge.core.wrench.executor

import com.idealista.android.challenge.core.wrench.type.Either

interface UseCaseExecutor {

    fun <Error, Result> execute(
        background: () -> Either<Error, Result>,
        ui: (Either<Error, Result>) -> Unit)
}