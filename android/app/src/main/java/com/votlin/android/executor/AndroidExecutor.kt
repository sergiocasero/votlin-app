package com.votlin.android.executor

import com.votlin.client.domain.executor.Executor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidExecutor : Executor {
    override val main: CoroutineDispatcher = Dispatchers.Main
}