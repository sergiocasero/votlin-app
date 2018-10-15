package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.model.Track

class TalksListPresenter(view: TalksListView, errorHandler: ErrorHandler) :
        Presenter<TalksListView>(view = view, errorHandler = errorHandler) {

    var track: Track? = null

    override fun initialize() {
        track = view.getTrack()
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun onViewVisible() {
        val trackValue = track
        if (trackValue != null) {
            // Todo: do request
        }
    }

}

interface TalksListView : Presenter.View {
    fun getTrack(): Track

    fun showTalks(talks: List<Track>)
}