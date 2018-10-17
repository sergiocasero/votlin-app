package com.votlin.client.domain

import com.votlin.client.domain.repository.Repository
import com.votlin.model.Talk

suspend fun getTalksUseCase(repository: Repository): List<Talk> = repository.getTalks()