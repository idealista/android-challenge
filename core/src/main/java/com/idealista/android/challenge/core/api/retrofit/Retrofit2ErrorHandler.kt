package com.idealista.android.challenge.core.api.retrofit

import com.idealista.android.challenge.core.api.model.CommonError
import com.idealista.android.challenge.core.api.model.CommonErrorEntity
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_BAD_REQUEST
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_FORBIDDEN
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_MOVED_PERMANENTLY
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_NOT_FOUND
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_SERVER_ERROR
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_UNAUTHORIZED
import com.idealista.android.challenge.core.api.retrofit.HttpStatus.TIME_OUT_ERROR
import com.idealista.android.challenge.core.api.model.toDomain
import com.idealista.android.challenge.core.wrench.type.Either
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

fun <T> Call<T>.run(): Either<CommonErrorEntity, T> = try {
    execute().handle()
} catch (exception: IOException) {
    if (exception.isCanceled()) Either.Left(CommonErrorEntity.Canceled("")) else Either.Left(
        CommonErrorEntity.NoNetwork(
            ""
        )
    )
}

fun <T> Response<T>.handle(): Either<CommonErrorEntity, T> {
    return if (isSuccessful) {
        body()?.let {
            Either.Right(it)
        } ?: Either.Right(Any() as T)
    }
    else {
        val error = when {
            isTimeOutError(code()) -> CommonErrorEntity.ServerError(errorBody()?.string())
            isForbiddenError(code()) -> CommonErrorEntity.Forbidden(errorBody()?.string())
            isNotFoundError(code()) -> CommonErrorEntity.NotFound(errorBody()?.string())
            isUnauthorizedError(code()) -> CommonErrorEntity.ClientError(errorBody()?.string())
            isServerError(code()) -> CommonErrorEntity.ServerError(errorBody()?.string())
            isBadRequestError(code()) -> CommonErrorEntity.ClientError(errorBody()?.string())
            isMovedPermanentlyError(code()) -> CommonErrorEntity.UnknownError(errorBody()?.string())
            else -> CommonErrorEntity.UnknownError(errorBody()?.string())
        }
        Either.Left(error)
    }
}

fun <T> Either<CommonErrorEntity, T>.toDomain(): Either<CommonError, T> = mapLeft(
    CommonErrorEntity::toDomain)

private fun isTimeOutError(code: Int): Boolean = code == TIME_OUT_ERROR

private fun isForbiddenError(code: Int): Boolean = code == HTTP_STATUS_FORBIDDEN

private fun isNotFoundError(code: Int): Boolean = code == HTTP_STATUS_NOT_FOUND

private fun isUnauthorizedError(code: Int): Boolean = code == HTTP_STATUS_UNAUTHORIZED

private fun isMovedPermanentlyError(code: Int): Boolean = code == HTTP_STATUS_MOVED_PERMANENTLY

private fun isServerError(code: Int): Boolean {
    val isGreaterOrEqualThan500 = code >= HTTP_STATUS_SERVER_ERROR
    val isLessThan600 = code < 600
    return isGreaterOrEqualThan500 && isLessThan600
}

private fun isBadRequestError(code: Int): Boolean {
    val isGreaterOrEqualThan400 = code >= HTTP_STATUS_BAD_REQUEST
    val isLessThan500 = code < HTTP_STATUS_SERVER_ERROR
    return isGreaterOrEqualThan400 && isLessThan500
}

private fun IOException.isCanceled() = "Canceled" == message

object HttpStatus {
    const val HTTP_STATUS_MOVED_PERMANENTLY = 301
    const val HTTP_STATUS_BAD_REQUEST = 400
    const val HTTP_STATUS_UNAUTHORIZED = 401
    const val HTTP_STATUS_FORBIDDEN = 403
    const val HTTP_STATUS_NOT_FOUND = 404
    const val HTTP_STATUS_SERVER_ERROR = 500
    const val TIME_OUT_ERROR = 499
}