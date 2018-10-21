package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.model.Talk
import com.votlin.model.Time
import com.votlin.model.Track
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.dom.*
import react.setState
import kotlin.js.Date

class HomeScreen : RootScreen<HomeProps, HomeState, TalksListView>(), TalksListView {

    init {
        state = HomeState()
    }

    private var track: Track = Track.ALL

    override fun componentDidMount() {
        super.componentDidMount()
        presenter.onViewVisible()
    }

    override val presenter: TalksListPresenter = TalksListPresenter(
            view = this,
            errorHandler = errorHandler,
            repository = repository,
            executor = executor)

    override fun RBuilder.render() {
        div("main") {
            div("toolbar") {
                img { attrs.src = "http://sergiocasero.es/votlin_logo.png" }
                h3 { +"Votlin" }
            }
            div("tabs") {
                a(classes = active(state.all)) {
                    +"ALL"
                    attrs.onClickFunction = { updateActiveTabAndLoadData(all = true) }
                }
                a(classes = active(state.business)) {
                    +"Business"
                    attrs.onClickFunction = { updateActiveTabAndLoadData(business = true) }
                }
                a(classes = active(state.development)) {
                    +"Development"
                    attrs.onClickFunction = { updateActiveTabAndLoadData(development = true) }
                }
                a(classes = active(state.maker)) {
                    +"Maker"
                    attrs.onClickFunction = { updateActiveTabAndLoadData(maker = true) }
                }
            }
            div {
                attrs.id = "talks"

                var time = Time(start = 0, end = 0)
                state.talks.forEach { talk ->

                    if (talk.time != time) {
                        time = talk.time

                        println(time.toFormattedDate())
                        if (time.start != 0L) div("line") { +time.toFormattedDate() }
                    }

                    div(classes = "card ${talk.track.toString().toLowerCase()}") {
                        attrs.onClickFunction = { presenter.onTalkClicked(talk) }
                        h3 { +talk.name }

                        if (talk.speakers.isNotEmpty()) {
                            talk.speakers.forEach { speaker ->
                                div("speaker") {
                                    img { attrs.src = speaker.photoUrl }
                                    span { +speaker.name }
                                }
                            }
                        }
                    }
                }
            }

            if (state.progress) {
                div("progress") {
                    img("progress") {
                        attrs.src = "https://mir-s3-cdn-cf.behance.net/project_modules/disp/35771931234507.564a1d2403b3a.gif"
                    }
                }
            }
        }
    }

    override fun getTrack(): Track = track

    override fun showError(error: String) {
        println(error)
    }

    override fun showMessage(message: String) {
        println(message)
    }

    override fun goToTalkScreen(id: Int) {
        println(id)
        props.onTalkSelected(id)
    }

    override fun showTalks(talks: List<Talk>) {
        setState {
            this.talks.clear()
            this.talks.addAll(talks)
        }
    }

    private fun active(active: Boolean): String = if (active) "active" else ""


    private fun updateActiveTabAndLoadData(all: Boolean = false,
                                           development: Boolean = false,
                                           business: Boolean = false,
                                           maker: Boolean = false) {
        setState {
            this.all = all
            this.development = development
            this.business = business
            this.maker = maker
        }

        track = when {
            all -> Track.ALL
            development -> Track.DEVELOPMENT
            business -> Track.BUSINESS
            maker -> Track.MAKER
            else -> Track.ALL
        }

        presenter.onViewVisible()
    }
}

class HomeState : ScreenState {
    var all: Boolean = true
    var development: Boolean = false
    var business: Boolean = false
    var maker: Boolean = false

    val talks = mutableListOf<Talk>()

    override var progress: Boolean = false
}

interface HomeProps : RProps {
    var onTalkSelected: (Int) -> Unit
}

fun RBuilder.home(onTalkSelected: (Int) -> Unit) = child(HomeScreen::class) {
    attrs.onTalkSelected = onTalkSelected
}

fun Time.toFormattedDate(): String = "${Date(start).getHours()}:${Date(start).getMinutes()} - ${Date(end).getHours()}:${Date(end).getMinutes()}"