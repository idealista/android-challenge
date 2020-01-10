package com.idealista.android.challenge.core.wrench.usecase

import com.idealista.android.challenge.core.wrench.executor.UseCaseExecutor
import com.idealista.android.challenge.core.wrench.type.Either

class UseCaseExecutable<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                                            private val ui: (Either<Error, Result>) -> Unit) {

    fun run(useCaseExecutor: UseCaseExecutor) = useCaseExecutor.execute(function, ui)

}