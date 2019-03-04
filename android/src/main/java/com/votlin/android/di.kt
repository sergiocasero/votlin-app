package com.votlin.android

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.votlin.android.error.AndroidErrorHandler
import com.votlin.android.executor.AndroidExecutor
import com.votlin.android.storage.AndroidLocalDataSource
import com.votlin.common.client.data.datasource.local.LocalDataSource
import com.votlin.common.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.common.client.data.datasource.remote.RemoteDataSource
import com.votlin.common.client.data.repository.CommonRepository
import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.client.domain.repository.Repository

fun app(context: Context) = Kodein.Module {
    bind<Context>() with singleton { context }
    bind<LocalDataSource>() with singleton { AndroidLocalDataSource(context = instance()) }
    bind<RemoteDataSource>() with singleton { CommonRemoteDataSource() }
    bind<Repository>() with singleton { CommonRepository(remote = instance(), local = instance()) }
    bind<Executor>() with singleton { AndroidExecutor() }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler() }
}