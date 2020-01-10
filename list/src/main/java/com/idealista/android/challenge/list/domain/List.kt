package com.idealista.android.challenge.list.domain

import com.idealista.android.challenge.core.model.entity.ListEntity
import kotlin.collections.List

data class List(
    val ads: List<Ad>)

fun ListEntity.toDomain() = List(elementList.map { it.toDomain() })