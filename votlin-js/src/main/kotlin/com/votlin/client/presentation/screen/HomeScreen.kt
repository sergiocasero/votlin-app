package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.model.Track
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.dom.a
import react.dom.div
import react.setState

class HomeScreen : RootScreen<HomeProps, HomeState, TalksView>(), TalksView {

    override fun componentDidMount() {
        super.componentDidMount()
        updateActive(all = true)
    }

    override val presenter: TalksPresenter = TalksPresenter(
            view = this,
            errorHandler = errorHandler)

    override fun RBuilder.render() {
        div("main") {
            div("tabs") {
                a {
                    +"ALL"
                    attrs.onClickFunction = {
                        updateActive(all = true)
                        presenter.onTrackSelected(Track.ALL)
                    }
                    attrs.classes = setOf(if (state.all) "active" else "")
                }
                a {
                    +"Business"
                    attrs.onClickFunction = {
                        updateActive(business = true)
                        presenter.onTrackSelected(Track.BUSINESS)
                    }
                    attrs.classes = setOf(if (state.business) "active" else "")
                }
                a {
                    +"Development"
                    attrs.onClickFunction = {
                        updateActive(development = true)
                        presenter.onTrackSelected(Track.DEVELOPMENT)
                    }
                    attrs.classes = setOf(if (state.development) "active" else "")
                }
                a {
                    +"Maker"
                    attrs.onClickFunction = {
                        updateActive(maker = true)
                        presenter.onTrackSelected(Track.MAKER)
                    }
                    attrs.classes = setOf(if (state.maker) "active" else "")
                }
            }
        }
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showMessage(message: String) {
        println(message)
    }

    override fun navigateToTrackList(track: Track) {
        props.onTrackSelected(track)
    }

    private fun updateActive(all: Boolean = false,
                             development: Boolean = false,
                             business: Boolean = false,
                             maker: Boolean = false) {
        setState {
            this.all = all
            this.development = development
            this.business = business
            this.maker = maker
        }
    }
}

interface HomeState : ScreenState {
    var all: Boolean
    var development: Boolean
    var business: Boolean
    var maker: Boolean
}

interface HomeProps : RProps {
    var onTrackSelected: (Track) -> Unit
}

fun RBuilder.home(onTrackSelected: (Track) -> Unit) = child(HomeScreen::class) {
    attrs.onTrackSelected = onTrackSelected
}