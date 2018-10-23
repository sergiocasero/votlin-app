package com.votlin.desktop.di

import com.votlin.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.client.data.repository.CommonRepository
import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.repository.Repository
import com.votlin.desktop.data.DesktopErrorHandler
import com.votlin.desktop.data.DesktopExecutor
import com.votlin.desktop.data.DesktopLocalDataSource

val executor: Executor = DesktopExecutor()

val errorHandler: ErrorHandler = DesktopErrorHandler()

val repository: Repository = CommonRepository(remote = CommonRemoteDataSource(), local = DesktopLocalDataSource())