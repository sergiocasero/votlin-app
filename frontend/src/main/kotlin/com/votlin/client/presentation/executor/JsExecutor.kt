package com.votlin.client.presentation.executor

import com.votlin.client.domain.executor.Executor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class JsExecutor : Executor {
    override val main: CoroutineDispatcher = Dispatchers.Default
}