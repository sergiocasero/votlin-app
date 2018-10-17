package com.votlin.client.data.datasource.remote

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

interface RemoteDataSource {
    fun getTalks(): List<Talk>
    fun getTalk(talkId: Int)
    fun getTrackTalks(track: Track): List<Talk>
    fun rateTalk(rate: Rate)
}