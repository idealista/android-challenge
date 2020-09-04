package com.idealista.android.challenge.core.api

import com.idealista.android.challenge.core.model.entity.ListEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    
    @GET("/v3/754f10b0-4fb7-4a6c-aa21-e7df9b6d2b92")
    fun list(): Call<ListEntity>

}