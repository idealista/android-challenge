package com.idealista.android.challenge.core.wrench.usecase

import com.idealista.android.challenge.core.wrench.executor.UseCaseExecutor
import com.idealista.android.challenge.core.wrench.type.Either

class Then<Error, Result> internal constructor(private val function: () -> Either<Error, Result>) {

    infix fun <Mapped> then(f: (Either<Error, Result>) -> Either<Error, Mapped>): Then<Error, Mapped> =
            Then { f(function.invoke()) }


    infix fun <Mapped> map(f: (Result) -> Mapped): Then<Error, Mapped> =
            Then { function.invoke().map { f(it) } }


    fun ui(ui: (Either<Error, Result>) -> Unit = {}): UseCaseExecutable<Error, Result> =
            UseCaseExecutable(function, ui)

    fun run(useCaseExecutor: UseCaseExecutor) =
            useCaseExecutor.execute(function, {})


}