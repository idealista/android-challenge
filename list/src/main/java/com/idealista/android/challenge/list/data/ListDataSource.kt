package com.idealista.android.challenge.list.data

import com.idealista.android.challenge.core.api.ListApi
import com.idealista.android.challenge.core.api.model.CommonError
import com.idealista.android.challenge.core.model.entity.ListEntity
import com.idealista.android.challenge.core.wrench.type.Either

class ListDataSource(private val api: ListApi) {

    fun list(): Either<CommonError, ListEntity> = api.list()

}