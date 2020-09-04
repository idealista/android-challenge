package com.idealista.android.challenge.core

import com.idealista.android.challenge.core.api.ApiClient
import com.idealista.android.challenge.core.api.ListApi
import com.idealista.android.challenge.core.wrench.executor.ChallengeUseCaseExecutor
import com.idealista.android.challenge.core.wrench.executor.UseCaseExecutor

object CoreAssembler {

    val executor: UseCaseExecutor by lazy { ChallengeUseCaseExecutor() }

    lateinit var stringsProvider: StringsProvider

    private val apiClient: ApiClient by lazy {
        ApiClient.Builder()
            .withEndPoint("https://run.mocky.io/")
            .create()
    }

    val listApi: ListApi by lazy { ListApi(apiClient) }

}