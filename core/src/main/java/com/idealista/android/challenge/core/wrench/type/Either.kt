package com.idealista.android.challenge.core.wrench.type

import com.idealista.android.challenge.core.wrench.type.Either.Left

sealed class Either<out L, out R> {

    data class Left<out T>(val value: T) : Either<T, Nothing>() {
        override fun isRight(): Boolean = false
        override fun isLeft(): Boolean = true
        override fun get(): Nothing = throw NoSuchElementException("Either.Right.value on Left")
    }

    data class Right<out T>(val value: T) : Either<Nothing, T>() {
        override fun isRight(): Boolean = true
        override fun isLeft(): Boolean = false
        override fun get(): T = value
    }

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean
    abstract fun get(): R

    inline fun <C, P> bimap(left: (L) -> C, right: (R) -> P): Either<C, P> = fold({ Left(left(it)) }, { Right(right(it)) })

    inline fun <C> fold(left: (L) -> C, right: (R) -> C): C = when (this) {
        is Left -> left(value)
        is Right -> right(value)
    }
    fun swap() = fold({ Right(it) }, { Left(it) })
    inline fun <C> map(f: (R) -> C): Either<L, C> =
            fold({ Left(it) }, { Right(f(it)) })

    inline fun <C> mapLeft(f: (L) -> C): Either<C, R> = fold({ Left(f(it)) }, { Right(it) })

    fun getOrNull(): R? = fold({ null }, { it })

    fun <B> Either<*, B>.getOrElse(default: () -> B): B = fold({ default() }, { it })
}

fun <L, R, S> Either<L, R>.flatMap(f: (R) -> Either<L, S>): Either<L, S> = fold({ Left(it) },{ f(it) })
