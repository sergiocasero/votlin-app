package com.votlin.client.domain.repository

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

class VotlinRepository(private val local: LocalDataSource,
                       private val remote: RemoteDataSource) : Repository {

    override fun getTalks(): List<Talk> = remote.getTalks()

    override fun getTalk(talkId: Int) = remote.getTalk(talkId = talkId)

    override fun getFavoriteTalks(): List<Talk> = local.getFavoriteTalks()

    override fun getTrackTalks(track: Track): List<Talk> = remote.getTrackTalks(track)

    override fun rateTalk(rate: Rate) {
        remote.rateTalk(rate)
        local.saveRate(rate)
    }

    override fun saveTalk(talk: Talk) = local.saveTalk(talk)
}