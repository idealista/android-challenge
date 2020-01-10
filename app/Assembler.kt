package com.idealista.android.challenge.assembler

import com.idealista.android.challenge.core.api.*
import com.idealista.android.challenge.detail.data.DetailDataSource
import com.idealista.android.challenge.detail.data.DetailRepository

object Assembler {

    private val apiClient: ApiClient by lazy {
        ApiClient.Builder()
            .withAuthConfig(AuthConfig("", ""))
            .withApikey(ApiKey("", ""))
            .withEndPoint("")
            .withDeviceId("")
            .create()
    }

    private val request = Request(apiClient, "es", "es")

    private val detailDataSource = DetailDataSource(DetailApi(request))

    val detailRepository = DetailRepository(detailDataSource)


}