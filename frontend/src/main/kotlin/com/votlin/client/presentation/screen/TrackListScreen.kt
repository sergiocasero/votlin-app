package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.client.presentation.di.errorHandler
import com.votlin.client.presentation.di.executor
import com.votlin.client.presentation.di.repository
import com.votlin.model.Talk
import com.votlin.model.Track
import react.RBuilder
import react.RProps
import react.dom.div
import react.setState

class TrackListScreen : RootScreen<TrackProps, TrackListState, TalksListView>(), TalksListView {

    init {
        state = TrackListState()
    }

    override val presenter: TalksListPresenter = TalksListPresenter(
            executor = executor,
            repository = repository,
            errorHandler = errorHandler,
            view = this
    )

    override fun componentDidMount() {
        super.componentDidMount()
        presenter.onTrackChanged()
    }

    override fun componentWillReceiveProps(nextProps: TrackProps) {
        super.componentWillReceiveProps(nextProps)
        if (nextProps.track != props.track) {
            presenter.onTrackChanged()
        }
    }

    override fun RBuilder.render() {
        div("list") {
            state.talks.forEach {
                div {
                    +it.name
                }
            }
        }
    }

    override fun showTalks(talks: List<Talk>) {
        println(props.track)

        talks.forEach { println(it.name) }

        setState { state.talks = talks }
    }

    override fun showError(error: String) {
        println("error")
    }

    override fun showMessage(message: String) {
        println("Message")
    }

    override fun getTrack(): Track = props.track

    override fun goToTalkScreen(id: Int) {
        // Not implemented yet
    }
}

class TrackListState : ScreenState {
    var talks: List<Talk> = mutableListOf()
    override var progress: Boolean = false
}

interface TrackProps : RProps {
    var track: Track
}

fun RBuilder.trackList(track: Track) = child(TrackListScreen::class) {
    attrs.track = track
}