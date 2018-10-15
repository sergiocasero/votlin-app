package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.model.Talk
import com.votlin.model.Track

class TalksPresenter(errorHandler: ErrorHandler, view: TalksView) :
        Presenter<TalksView>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        // Nothing to do yet
        view.showTalks(listOf(
                Talk(id = 1,
                        name = "Talk test",
                        description = "Lorem lorem",
                        speakers = listOf(),
                        datetimeStart = 123123,
                        datetimeEnd = 123123,
                        track = Track.Development("Room Test")
                )))
    }

    override fun destroy() {
        // Nothing to do yet
    }

}

interface TalksView : Presenter.View {
    fun showTalks(talks: List<Talk>)
}