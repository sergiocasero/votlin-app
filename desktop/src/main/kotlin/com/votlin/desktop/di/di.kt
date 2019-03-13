package com.votlin.desktop.di

import com.votlin.common.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.common.client.data.repository.CommonRepository
import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.client.domain.repository.Repository
import com.votlin.desktop.data.DesktopErrorHandler
import com.votlin.desktop.data.DesktopExecutor
import com.votlin.desktop.data.DesktopLocalDataSource

val executor: Executor = DesktopExecutor()

val errorHandler: ErrorHandler = DesktopErrorHandler()

val repository: Repository = CommonRepository(remote = CommonRemoteDataSource(), local = DesktopLocalDataSource())