package com.votlin.client.data.datasource.remote

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.TalksResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON

class CommonRemoteDataSource : RemoteDataSource {

    private val endPoint: String = "http://sergiocasero.es:10000"

    private val client: HttpClient = HttpClient()

    override suspend fun getTalks(): List<Talk> =
            JSON.parse<TalksResponse>(client.get { apiUrl("talk") }).talks

    override suspend fun getTalk(talkId: Int): Talk =
            JSON.parse(client.get { apiUrl("talk/$talkId") })

    override suspend fun getTalksByTrack(track: String): List<Talk> =
            JSON.parse<TalksResponse>(client.get { apiUrl("talk/$track") }).talks

    override suspend fun rateTalk(rate: Rate): Unit = client.post {
        apiUrl("talk")
        body = JSON.stringify(rate)
    }

    protected fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}