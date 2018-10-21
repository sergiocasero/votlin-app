package com.votlin.android

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.votlin.android.api.AndroidRemoteDataSource
import com.votlin.android.error.AndroidErrorHandler
import com.votlin.android.executor.AndroidExecutor
import com.votlin.android.storage.AndroidLocalDataSource
import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource
import com.votlin.client.data.repository.CommonRepository
import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.repository.Repository

fun app(context: Context) = Kodein.Module {
    bind<Context>() with singleton { context }
    bind<LocalDataSource>() with singleton { AndroidLocalDataSource(context = instance()) }
    bind<RemoteDataSource>() with singleton { AndroidRemoteDataSource() }
    bind<Repository>() with singleton { CommonRepository(remote = instance(), local = instance()) }
    bind<Executor>() with singleton { AndroidExecutor() }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler() }
}