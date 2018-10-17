package com.votlin.client.domain.repository

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

interface Repository {
    suspend fun getTalks(): List<Talk>

    fun saveTalk(talk: Talk)

    fun getTrackTalks(track: Track): List<Talk>

    fun rateTalk(rate: Rate)

    fun getTalk(talkId: Int)

    fun getFavoriteTalks(): List<Talk>
}