package com.votlin.client.domain.executor

import kotlinx.coroutines.CoroutineDispatcher

interface Executor {
    val new: CoroutineDispatcher
    val main: CoroutineDispatcher
}