package com.votlin.common.client.presentation

import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.model.Track

class TalksPresenter(
    executor: Executor,
    errorHandler: ErrorHandler,
    view: TalksView) :
        Presenter<TalksView>(errorHandler = errorHandler, view = view, executor = executor) {

    override fun initialize() {
        // Nothing to do yet
    }

    fun onTrackSelected(track: Track) {
        view.navigateToTrackList(track)
    }

}

interface TalksView : Presenter.View {
    fun navigateToTrackList(track: Track)
}