package com.idealista.android.challenge.core.wrench.type

sealed class Option<out S> {

    data class Some<out S>(val value: S) : Option<S>()

    object None : Option<Nothing>()

    inline fun <C> fold(ifEmpty: () -> C, some: (S) -> C): C = when (this) {
        None -> ifEmpty()
        is Some -> some(value)
    }

    inline fun <C> map(f: (S) -> C): Option<C> = fold({ None }, { Some(f(it)) })

    fun getOrNull(): S? = fold({ null }, { it })

}

fun <T> T.pure() : Option<T> = Option.Some(this)

fun <T> T?.toOption(): Option<T> = if (this != null) {
    Option.Some(this)
} else {
    Option.None
}

fun <B> Option<B>.getOrElse(default: () -> B): B = fold({ default() }, { it })

fun <R, S> Option<R>.flatMap(f: (R) -> Option<S>): Option<S> = when (this) {
    Option.None -> Option.None
    is Option.Some -> f(value)
}
