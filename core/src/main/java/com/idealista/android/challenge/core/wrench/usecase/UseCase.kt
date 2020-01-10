package com.idealista.android.challenge.core.wrench.usecase

import com.idealista.android.challenge.core.wrench.type.Either

class UseCase<Error, Result> {

    fun bg(background: () -> Either<Error, Result>): Then<Error, Result> =
            Then(background)

}

