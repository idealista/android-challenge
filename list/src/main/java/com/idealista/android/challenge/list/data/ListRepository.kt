package com.idealista.android.challenge.list.data

import com.idealista.android.challenge.core.api.model.CommonError
import com.idealista.android.challenge.core.wrench.type.Either
import com.idealista.android.challenge.list.domain.List

class ListRepository(private val dataSource: ListDataSource) {

    fun list(): Either<CommonError, List> = dataSource.list().map { it.toDomain() }

}