package com.votlin.android.api

import com.votlin.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.model.*
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

class AndroidRemoteDataSource: CommonRemoteDataSource() {
    private val httpClientConfig: HttpClientConfig<*>.() -> Unit  = {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(Talk::class, Talk.serializer())
                setMapper(TalksResponse::class, TalksResponse.serializer())
                setMapper(Speaker::class, Speaker.serializer())
                setMapper(Rate::class, Rate.serializer())
                setMapper(Time::class, Time.serializer())
            }
        }
        install(ExpectSuccess)
    }

    override val client = HttpClient(httpClientConfig)
}