package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.model.Speaker
import com.votlin.model.Talk
import com.votlin.model.Time
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
            view.showTalks(mockTalks().filter { trackValue == Track.ALL || trackValue == it.track })
        }
    }

    fun onTalkClicked(talk: Talk) = view.goToTalkScreen(talk.id)

    // todo: remove this
    private fun mockTalks() = listOf(
            Talk(id = 0,
                    name = "Talk 1",
                    description = "Description 1",
                    speakers = listOf(
                            Speaker("", "", "Name 1", "", ""),
                            Speaker("", "", "Name 2", "", "")
                    ),
                    track = Track.MAKER,
                    time = Time(123123, 123123)),
            Talk(id = 1,
                    name = "Talk 2",
                    description = "Description 2",
                    speakers = listOf(
                            Speaker("", "", "Name 1", "", ""),
                            Speaker("", "", "Name 2", "", "")
                    ),
                    track = Track.DEVELOPMENT,
                    time = Time(123123, 123123)),
            Talk(id = 2,
                    name = "Talk 3",
                    description = "Description 3",
                    speakers = listOf(
                            Speaker("", "", "Name 1", "", ""),
                            Speaker("", "", "Name 2", "", "")
                    ),
                    track = Track.BUSINESS,
                    time = Time(123123, 123123)),
            Talk(id = 3,
                    name = "Talk 4",
                    description = "Description 4",
                    speakers = listOf(
                            Speaker("", "", "Name 1", "", ""),
                            Speaker("", "", "Name 2", "", "")
                    ),
                    track = Track.MAKER,
                    time = Time(123123, 123123)),
            Talk(id = 4,
                    name = "Talk 5",
                    description = "Description 5",
                    speakers = listOf(
                            Speaker("", "", "Name 1", "", ""),
                            Speaker("", "", "Name 2", "", "")
                    ),
                    track = Track.DEVELOPMENT,
                    time = Time(123123, 123123))
    )

}

interface TalksListView : Presenter.View {
    fun getTrack(): Track
    fun showTalks(talks: List<Talk>)
    fun goToTalkScreen(id: Int)
}