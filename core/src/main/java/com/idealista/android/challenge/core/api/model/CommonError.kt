package com.idealista.android.challenge.core.api.model

sealed class CommonError {
    object NoNetwork : CommonError()
    object ServerError : CommonError()
    object ClientError : CommonError()
    object Forbidden : CommonError()
    object UnknownError : CommonError()
    object NotFound : CommonError()
    object Canceled : CommonError()
}