package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.model.Talk
import com.votlin.model.Track
import react.RBuilder
import react.RProps
import react.dom.div

class HomeScreen : RootScreen<HomeProps, HomeState, TalksListView>(), TalksListView {

    override val presenter: TalksListPresenter = TalksListPresenter(
            view = this,
            errorHandler = errorHandler,
            repository = repository,
            executor = executor)

    override fun RBuilder.render() {
        div("main") {
            +"Hello from splash screen"
        }
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showMessage(message: String) {
        println(message)
    }

    override fun getTrack(): Track = Track.ALL

    override fun showTalks(talks: List<Talk>) {
        // TODO
    }

    override fun goToTalkScreen(id: Int) {
        // TODO
    }
}

interface HomeState : ScreenState

interface HomeProps : RProps

fun RBuilder.home() = child(HomeScreen::class) {}