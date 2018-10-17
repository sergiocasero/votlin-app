package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler

class TalksPresenter(errorHandler: ErrorHandler, view: TalksView) :
        Presenter<TalksView>(errorHandler = errorHandler, view = view) {

    override fun initialize() {
        // Nothing to do yet
    }

    override fun destroy() {
        // Nothing to do yet
    }

}

interface TalksView : Presenter.View