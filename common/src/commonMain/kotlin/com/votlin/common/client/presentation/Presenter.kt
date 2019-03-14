package com.votlin.common.client.presentation

import com.votlin.common.client.domain.error.Error
import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

abstract class Presenter<out V : Presenter.View>(
    protected val errorHandler: ErrorHandler,
    val view: V,
    executor: Executor
) {

    private val job = SupervisorJob()

    protected val scope = CoroutineScope(job + executor.main)

    abstract fun initialize()

    fun destroy() = job.cancel()

    protected fun onError(callback: (String) -> Unit): (Error) -> Unit = {
        view.hideProgress()

        callback(errorHandler.convert(it))
    }

    interface View {
        fun showProgress()

        fun hideProgress()

        fun showError(error: String)

        fun showMessage(message: String)
    }
}