package com.votlin.client.data

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.TalksResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON

class JsRemoteDataSource : CommonRemoteDataSource() {

    companion object {
        const val ENDPOINT = "http://sergiocasero.es:10000"
    }

    override val client: HttpClient = HttpClient {}

    override suspend fun getTalks(): List<Talk> {
        return JSON.parse<TalksResponse>(client.get {
            url {
                takeFrom(ENDPOINT)
                encodedPath = "talk"
            }
        }).talks
    }

    override suspend fun getTalksByTrack(track: String): List<Talk> =
            JSON.parse<TalksResponse>(client.get {
                url {
                    takeFrom(ENDPOINT)
                    encodedPath = "talk/$track"
                }
            }).talks


    override suspend fun getTalk(talkId: Int): Talk =
            JSON.parse(client.get {
                url {
                    takeFrom(ENDPOINT)
                    encodedPath = "talk/$talkId"
                }
            })

    override suspend fun rateTalk(rate: Rate): Unit = client.post {
        apiUrl("talk/rate")
        body = JSON.stringify(rate)
    }
}

class JsLocalDataSource : LocalDataSource {
    override fun getFavoriteTalks(): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRate(rate: Rate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTalk(talk: Talk) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}