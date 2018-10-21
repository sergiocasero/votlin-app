package com.votlin.client.data.repository

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource
import com.votlin.client.domain.repository.Repository
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

class CommonRepository(private val local: LocalDataSource,
                       private val remote: RemoteDataSource) : Repository {

    override suspend fun getTalks(): List<Talk> = remote.getTalks()

    override suspend fun getTalk(talkId: Int): Talk = remote.getTalk(talkId = talkId)

    override suspend fun getFavoriteTalks(): List<Talk> = local.getFavoriteTalks()

    override suspend fun getTalksByTrack(track: Track): List<Talk> = remote.getTalksByTrack(track.toString().toLowerCase())

    override suspend fun rateTalk(rate: Rate) {
        remote.rateTalk(rate)
        local.saveRate(rate)
    }

    override fun saveTalk(talk: Talk) = local.saveTalk(talk)

    override fun getRate(talkId: Int): Int = local.getRate(talkId)
}