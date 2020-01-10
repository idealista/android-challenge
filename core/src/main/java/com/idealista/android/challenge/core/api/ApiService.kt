package com.idealista.android.challenge.core.api

import com.idealista.android.challenge.core.model.entity.ListEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    
    @GET("/bins/93tqm")
    fun list(): Call<ListEntity>

}