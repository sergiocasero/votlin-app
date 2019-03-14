package com.votlin.common.client.presentation

import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.client.domain.repository.Repository
import com.votlin.common.client.domain.usecase.getTalkDetail
import com.votlin.common.client.domain.usecase.getTalkRate
import com.votlin.common.client.domain.usecase.rateTalk
import com.votlin.common.model.Rate
import com.votlin.common.model.Talk
import kotlinx.coroutines.launch


class DetailPresenter(
    private val repository: Repository,
    executor: Executor,
    errorHandler: ErrorHandler,
    view: DetailView
) : Presenter<DetailView>(errorHandler, view, executor) {

    override fun initialize() {
        view.showProgress()
        scope.launch {
            val talk = getTalkDetail(view.getTalkId(), repository)

            view.showTalk(talk)
            view.hideProgress()
        }

        val rate = getTalkRate(view.getTalkId(), repository)
        view.showRate(rate)
    }

    fun onBackClicked() {
        view.navigateToList()
    }

    fun onRateChange(rate: Int) {
        scope.launch {
            rateTalk(Rate(id = view.getTalkId(), value = rate), repository)
            view.showRate(rate)
        }
    }

}

interface DetailView : Presenter.View {
    fun getTalkId(): Int
    fun showTalk(talk: Talk)
    fun navigateToList()
    fun showRate(rate: Int)
}