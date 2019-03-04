package com.votlin.common.client.domain.repository

import com.votlin.common.model.Rate
import com.votlin.common.model.Talk
import com.votlin.common.model.Track

interface Repository {
    suspend fun getTalks(): List<Talk>

    fun saveTalk(talk: Talk)

    suspend fun getTalksByTrack(track: Track): List<Talk>

    suspend fun rateTalk(rate: Rate)

    suspend fun getTalk(talkId: Int): Talk

    suspend fun getFavoriteTalks(): List<Talk>

    fun getRate(talkId: Int): Int
}