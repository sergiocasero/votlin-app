package com.votlin.common.client.data.datasource.remote

import com.votlin.common.model.Rate
import com.votlin.common.model.Talk

interface RemoteDataSource {
    suspend fun getTalks(): List<Talk>
    suspend fun getTalk(talkId: Int): Talk
    suspend fun getTalksByTrack(track: String): List<Talk>
    suspend fun rateTalk(rate: Rate)
}