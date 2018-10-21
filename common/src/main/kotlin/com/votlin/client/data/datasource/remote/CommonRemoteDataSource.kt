package com.votlin.client.data.datasource.remote

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.TalksResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.takeFrom

abstract class CommonRemoteDataSource : RemoteDataSource {

    private val endPoint: String = "http://sergiocasero.es:10000"

    abstract val client: HttpClient

    override suspend fun getTalks(): List<Talk> = client.get<TalksResponse> { apiUrl("talk") }.talks

    override suspend fun getTalk(talkId: Int): Talk = client.get { apiUrl("talk/$talkId") }

    override suspend fun getTalksByTrack(track: String): List<Talk> = client.get<TalksResponse> { apiUrl("talk/$track") }.talks

    override suspend fun rateTalk(rate: Rate): Unit = client.post {
        apiUrl("talk")
        body = rate
    }

    protected fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}