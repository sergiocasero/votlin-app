package com.votlin.client.domain.repository

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

interface Repository {
    suspend fun getTalks(): List<Talk>

    fun saveTalk(talk: Talk)

    suspend fun getTalksByTrack(track: Track): List<Talk>

    suspend fun rateTalk(rate: Rate)

    suspend fun getTalk(talkId: Int): Talk

    suspend fun getFavoriteTalks(): List<Talk>

    fun getRate(talkId: Int): Int
}