package com.votlin.client.presentation

import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.model.Track

class TalksPresenter(errorHandler: ErrorHandler, view: TalksView) :
        Presenter<TalksView>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        // Nothing to do yet
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun onTrackSelected(track: Track) {
        view.navigateToTrackList(track)
    }

}

interface TalksView : Presenter.View {
    fun navigateToTrackList(track: Track)
}