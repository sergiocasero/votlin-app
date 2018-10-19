package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.dom.a
import react.dom.div
import react.dom.span
import react.setState

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
                state.talks.forEach {
                    span { +it.name }
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
        // Nothing to do yet
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
    var onTalkSelected: (Talk) -> Unit
}

fun RBuilder.home(onTalkSelected: (Talk) -> Unit) = child(HomeScreen::class) {
    attrs.onTalkSelected = onTalkSelected
}