package com.votlin.common.client.domain.usecase

import com.votlin.common.client.domain.repository.Repository
import com.votlin.common.model.Rate
import com.votlin.common.model.Talk
import com.votlin.common.model.Track

suspend fun getAllTalks(repository: Repository): List<Talk> = repository.getTalks()

suspend fun getTalkDetail(id: Int, repository: Repository): Talk = repository.getTalk(talkId = id)

suspend fun getTalksByTrack(track: Track, repository: Repository): List<Talk> =
    repository.getTalksByTrack(track = track)

suspend fun rateTalk(rate: Rate, repository: Repository): Unit = repository.rateTalk(rate = rate)

fun saveTalk(talk: Talk, repository: Repository): Unit = repository.saveTalk(talk = talk)

fun getTalkRate(talkId: Int, repository: Repository): Int = repository.getRate(talkId = talkId)

suspend fun getFavoriteTalks(repository: Repository): List<Talk> = repository.getFavoriteTalks()