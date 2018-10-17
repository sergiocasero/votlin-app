package com.votlin.client.data.datasource.remote

import com.votlin.model.*
import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom

class CommonRemoteDataSource : RemoteDataSource {

    private val endPoint: String = "http://sergiocasero.es:10000"

    private val client = HttpClient {
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

    override suspend fun getTalks(): List<Talk> = client.get<TalksResponse> { apiUrl("talk") }.talks

    override suspend fun getTalk(talkId: Int): Talk = client.get<TalkDto> {
        apiUrl("talk/$talkId")
    }.toModel()

    override fun getTrackTalks(track: Track): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rateTalk(rate: Rate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}