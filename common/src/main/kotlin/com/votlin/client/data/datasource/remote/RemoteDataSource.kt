package com.votlin.client.data.datasource.remote

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

interface RemoteDataSource {
    suspend fun getTalks(): List<Talk>
    suspend fun getTalk(talkId: Int): Talk
    fun getTrackTalks(track: Track): List<Talk>
    fun rateTalk(rate: Rate)
}