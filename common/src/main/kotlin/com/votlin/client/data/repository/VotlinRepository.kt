package com.votlin.client.domain.repository

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource

class VotlinRepository(private val local: LocalDataSource,
                       private val remote: RemoteDataSource) : Repository {
}