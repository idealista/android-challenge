package com.idealista.android.challenge.list.ui

import com.idealista.android.challenge.core.CoreAssembler
import com.idealista.android.challenge.core.R
import com.idealista.android.challenge.core.model.Operation
import com.idealista.android.challenge.core.model.Typology
import com.idealista.android.challenge.core.model.string
import com.idealista.android.challenge.list.domain.Ad

data class AdModel(
    val id: String,
    val thumbnail: String,
    val price: String,
    val title: String
)

fun Ad.toModel() =
    AdModel(
        id,
        thumbnail,
        formatPrice(price),
        formatTitle(typology, operation))

private fun formatPrice(price: Double) = "$price €"
private fun formatTitle(typology: Typology, operation: Operation) =
    CoreAssembler.stringsProvider.string(
        R.string.typology_at_operation,
        typology.string(),
        operation.string().toLowerCase())