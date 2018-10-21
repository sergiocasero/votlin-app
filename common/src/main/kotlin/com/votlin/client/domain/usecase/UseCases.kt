package com.votlin.client.domain.usecase

import com.votlin.client.domain.repository.Repository
import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

suspend fun getAllTalks(repository: Repository): List<Talk> = repository.getTalks()

suspend fun getTalkDetail(id: Int, repository: Repository): Talk = repository.getTalk(talkId = id)

suspend fun getTalksByTrack(track: Track, repository: Repository): List<Talk> = repository.getTalksByTrack(track)

suspend fun rateTalk(rate: Rate, repository: Repository): Unit = repository.rateTalk(rate)

fun saveTalk(talk: Talk, repository: Repository): Unit = repository.saveTalk(talk)

fun getTalkRate(talkId: Int, repository: Repository): Int = repository.getRate(talkId)

suspend fun getFavoriteTalks(repository: Repository): List<Talk> = repository.getFavoriteTalks()