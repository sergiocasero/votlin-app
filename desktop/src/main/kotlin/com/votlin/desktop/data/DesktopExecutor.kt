package com.votlin.desktop.data

import com.votlin.client.domain.executor.Executor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DesktopExecutor : Executor {
    override val main: CoroutineDispatcher = Dispatchers.Unconfined
}