package com.votlin.client.presentation.di

import com.votlin.client.data.JsLocalDataSource
import com.votlin.common.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.common.client.data.repository.CommonRepository
import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.client.domain.repository.Repository
import com.votlin.client.presentation.error.JsErrorHandler
import com.votlin.client.presentation.executor.JsExecutor

val executor: Executor = JsExecutor()

val errorHandler: ErrorHandler = JsErrorHandler()

val remote = CommonRemoteDataSource()

val local = JsLocalDataSource()

val repository: Repository = CommonRepository(remote = remote, local = local)