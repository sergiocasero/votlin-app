package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.executor.Executor
import com.votlin.client.domain.getAllTalks
import com.votlin.client.domain.repository.Repository
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TalksListPresenter(private val executor: Executor,
                         private val repository: Repository,
                         view: TalksListView,
                         errorHandler: ErrorHandler) :
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
            view.showProgress()

            getTalks {
                view.showTalks(it)
                view.hideProgress()
            }
        }
    }

    fun onTalkClicked(talk: Talk) = view.goToTalkScreen(talk.id)

    private fun getTalks(callback: (List<Talk>) -> Unit) {
        GlobalScope.launch(executor.new) {
            val talks = getAllTalks(repository)
            withContext(executor.main) {
                callback(talks)
            }
        }
    }

}

interface TalksListView : Presenter.View {
    fun getTrack(): Track
    fun showTalks(talks: List<Talk>)
    fun goToTalkScreen(id: Int)
}