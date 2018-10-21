package com.votlin.desktop.di

import com.votlin.client.data.repository.CommonRepository
import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.repository.Repository
import com.votlin.desktop.data.DesktopErrorHandler
import com.votlin.desktop.data.DesktopExecutor
import com.votlin.desktop.data.DesktopLocalDataSource
import com.votlin.desktop.data.DesktopRemoteDataSource

val executor: Executor = DesktopExecutor()

val errorHandler: ErrorHandler = DesktopErrorHandler()

val repository: Repository = CommonRepository(remote = DesktopRemoteDataSource(), local = DesktopLocalDataSource())