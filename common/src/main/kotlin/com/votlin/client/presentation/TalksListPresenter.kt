package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.repository.Repository
import com.votlin.client.domain.usecase.getAllTalks
import com.votlin.client.domain.usecase.getTalksByTrack
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TalksListPresenter(
    private val executor: Executor,
    private val repository: Repository,
    view: TalksListView,
    errorHandler: ErrorHandler
) : Presenter<TalksListView>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        // Nothing to do
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun viewLoaded(track: Track) {
        getTalks(track)
    }

    fun onTalkClicked(talk: Talk) {
        view.goToTalkScreen(talk.id)
    }

    private fun getTalks(track: Track) {
        view.showProgress()
        GlobalScope.launch(context = executor.main) {
            val talks = when (track) {
                Track.ALL -> getAllTalks(repository)
                else -> getTalksByTrack(track, repository)
            }

            view.showTalks(talks)
            view.hideProgress()
        }
    }

}

interface TalksListView : Presenter.View {
    fun showTalks(talks: List<Talk>)
    fun goToTalkScreen(id: Int)
}