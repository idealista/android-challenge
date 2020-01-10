package com.idealista.android.challenge.core.model.entity

data class AdEntity(
    val propertyCode: String?,
    val thumbnail: String?,
    val price: Double?,
    val propertyType: String?,
    val operation: String?,
    val detailUrl: String?,
    val multimedia: MultimediasEntity?
)