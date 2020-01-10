package com.idealista.android.challenge.core.model

import com.idealista.android.challenge.core.CoreAssembler
import com.idealista.android.challenge.core.R

sealed class Operation {
    object Rent : Operation()
    object Sale : Operation()
    object Unknown : Operation()

    companion object {
        fun from(operation: String): Operation {
            return when (operation) {
                "rent" -> Rent
                "sale" -> Sale
            }
        }
    }

    fun stringId(): Int {
        return when (this) {
            Rent -> R.string.operation_rent
            Sale -> R.string.operation_sale
            Unknown -> 0
        }
    }
}

fun Operation.string() = if (this !is Operation.Unknown) CoreAssembler.stringsProvider.string(stringId()) else ""