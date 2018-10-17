package com.votlin.client.domain.repository

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

interface Repository {
    suspend fun getTalks(): List<Talk>

    fun saveTalk(talk: Talk)

    fun getTrackTalks(track: Track): List<Talk>

    fun rateTalk(rate: Rate)

    suspend fun getTalk(talkId: Int): Talk

    fun getFavoriteTalks(): List<Talk>
}