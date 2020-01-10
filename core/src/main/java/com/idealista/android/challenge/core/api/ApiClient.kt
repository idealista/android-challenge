package com.idealista.android.challenge.core.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ApiClient private constructor(private val endPoint: String) {

    private val retrofit: Retrofit by lazy {
        val builder = Retrofit.Builder()
        builder.baseUrl(this.endPoint)

        val clientBuilder = OkHttpClient.Builder()
        builder.client(clientBuilder.build())
        builder.addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()))
        builder.build()
    }

    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }

    private fun getGsonBuilder(): GsonBuilder {
        val builder = GsonBuilder()

        builder.registerTypeAdapter(Date::class.java,
            JsonDeserializer { json, typeOfT, context -> Date(json.asJsonPrimitive.asLong) })

        return builder
    }

    class Builder {
        private lateinit var endPoint: String

        fun withEndPoint(endPoint: String): Builder = apply {
            this.endPoint = endPoint
        }

        fun create(): ApiClient = ApiClient(endPoint)
    }
}
