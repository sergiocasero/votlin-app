package com.votlin.client.presentation

import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor
import com.votlin.common.client.domain.repository.Repository
import com.votlin.common.client.domain.usecase.getAllTalks
import com.votlin.common.client.domain.usecase.getTalksByTrack
import com.votlin.common.model.Talk
import com.votlin.common.model.Track
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TalksListPresenter(
    private val executor: Executor,
    private val repository: Repository,
    view: TalksListView,
    errorHandler: ErrorHandler
) : Presenter<TalksListView>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        getTalks(Track.ALL)
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun onTrackChanged(track: Track) {
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