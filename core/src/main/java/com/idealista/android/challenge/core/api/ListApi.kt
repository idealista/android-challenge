package com.idealista.android.challenge.core.api

import com.idealista.android.challenge.core.api.model.CommonError
import com.idealista.android.challenge.core.model.entity.ListEntity
import com.idealista.android.challenge.core.api.retrofit.run
import com.idealista.android.challenge.core.api.retrofit.toDomain
import com.idealista.android.challenge.core.wrench.type.Either

class ListApi(private val client: ApiClient) {

    fun list(): Either<CommonError, ListEntity> = with (client) {
        apiService.list().run().toDomain()
    }

}