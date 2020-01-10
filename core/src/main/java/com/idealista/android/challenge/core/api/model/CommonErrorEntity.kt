package com.idealista.android.challenge.core.api.model

sealed class CommonErrorEntity(open val body: String?) {
    data class NoNetwork(override val body: String?) : CommonErrorEntity(body)
    data class ServerError(override val body: String?) : CommonErrorEntity(body)
    data class ClientError(override val body: String?) : CommonErrorEntity(body)
    data class Forbidden(override val body: String?) : CommonErrorEntity(body)
    data class UnknownError(override val body: String?) : CommonErrorEntity(body)
    data class NotFound(override val body: String?) : CommonErrorEntity(body)
    data class Canceled(override val body: String?) : CommonErrorEntity(body)
}

fun CommonErrorEntity.toDomain(): CommonError = when (this) {
    is CommonErrorEntity.NoNetwork -> CommonError.NoNetwork
    is CommonErrorEntity.ServerError -> CommonError.ServerError
    is CommonErrorEntity.ClientError -> CommonError.ClientError
    is CommonErrorEntity.Forbidden -> CommonError.Forbidden
    is CommonErrorEntity.UnknownError -> CommonError.UnknownError
    is CommonErrorEntity.NotFound -> CommonError.NotFound
    is CommonErrorEntity.Canceled -> CommonError.Canceled
}