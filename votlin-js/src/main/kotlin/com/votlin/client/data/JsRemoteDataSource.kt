package com.votlin.client.data

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.TalksResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON

class JsRemoteDataSource : CommonRemoteDataSource() {
    override val client: HttpClient = HttpClient {}

    override suspend fun getTalks(): List<Talk> {
        return JSON.parse<TalksResponse>(client.get {
            url {
                takeFrom("http://sergiocasero.es:10000")
                encodedPath = "talk"
            }
        }).talks
    }

    override suspend fun getTalksByTrack(track: String): List<Talk> {
        return JSON.parse<TalksResponse>(client.get {
            url {
                takeFrom("http://sergiocasero.es:10000")
                encodedPath = "talk/$track"
            }
        }).talks
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