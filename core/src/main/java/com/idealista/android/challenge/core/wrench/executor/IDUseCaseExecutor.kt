package com.idealista.android.challenge.core.wrench.executor

import com.idealista.android.challenge.core.wrench.type.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class ChallengeUseCaseExecutor : UseCaseExecutor {

    override fun <Error, Result> execute(
        background: () -> Either<Error, Result>,
        ui: (Either<Error, Result>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) { background.invoke() }
            ui.invoke(deferred.await())
        }
    }
}