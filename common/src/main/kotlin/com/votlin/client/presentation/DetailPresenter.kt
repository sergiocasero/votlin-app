package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.getTalkDetail
import com.votlin.client.domain.repository.Repository
import com.votlin.model.Talk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailPresenter(private val repository: Repository,
                      private val executor: Executor,
                      errorHandler: ErrorHandler,
                      view: DetailView)
    : Presenter<DetailView>(errorHandler, view) {

    override fun initialize() {
        view.showProgress()
        GlobalScope.launch(executor.new) {
            val talk = getTalkDetail(view.getTalkId(), repository)

            withContext(executor.main) {
                view.showTalk(talk)
                view.hideProgress()
            }
        }
    }

    override fun destroy() {
        // Nothing to do yet
    }
}

interface DetailView : Presenter.View {
    fun getTalkId(): Int
    fun showTalk(talk: Talk)
}