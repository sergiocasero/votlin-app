package com.votlin.client.presentation.screen

import com.votlin.common.client.constants.*
import com.votlin.client.presentation.di.errorHandler
import com.votlin.client.presentation.di.executor
import com.votlin.client.presentation.di.repository
import com.votlin.client.presentation.navigator.Screen
import com.votlin.common.client.presentation.DetailPresenter
import com.votlin.common.client.presentation.DetailView
import com.votlin.common.model.Talk
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.dom.*
import react.setState

class DetailScreen : RootScreen<DetailProps, DetailState, DetailView>(), DetailView {


    override val presenter: DetailPresenter = DetailPresenter(
            repository = repository,
            executor = executor,
            errorHandler = errorHandler,
            view = this
    )

    override fun RBuilder.render() {
        div("detail") {
            if (state.talk != undefined) {
                div("toolbar") {
                    img {
                        attrs.src = backUrl
                        attrs.onClickFunction = { presenter.onBackClicked() }
                    }
                    span { +state.talk.name }
                }

                div(classes = "card ${state.talk.track.toString().toLowerCase()}") {
                    h3 { +state.talk.track.toString().toLowerCase().capitalize() }
                }
                span("description") { +state.talk.description }

                println(state.rate)

                div("rate") {
                    for (star in 1..5) {

                        img {
                            attrs.src = if (state.rate >= star) starUrl else outlineStarUrl
                            attrs.onClickFunction = { presenter.onRateChange(star) }
                        }
                    }
                }

                div("speakers") {
                    state.talk.speakers.forEach { speaker ->
                        div(classes = "speakerCard") {
                            img(classes = "photo") { attrs.src = speaker.photoUrl }
                            h3 { +speaker.name }
                            span { +speaker.bio }
                            div("social") {
                                if (speaker.twitter.isNotBlank()) {
                                    a {
                                        attrs.href = speaker.twitter
                                        img { attrs.src = twitterUrl }
                                    }
                                }
                                if (speaker.linkedin.isNotBlank()) {
                                    a {
                                        attrs.href = speaker.linkedin
                                        attrs.target = "_blank"
                                        img { attrs.src = linkedinUrl }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    override fun showError(error: String) {
        println("Error: $error")
    }

    override fun showMessage(message: String) {
        println("Message: $message")
    }

    override fun getTalkId(): Int = props.talkId

    override fun showTalk(talk: Talk) {
        setState { this.talk = talk }
    }

    override fun navigateToList() {
        props.onBack(Screen.HOME)
    }

    override fun showRate(rate: Int) {
        setState { this.rate = rate }
    }
}

interface DetailState : ScreenState {
    var talk: Talk
    var rate: Int
}

interface DetailProps : RProps {
    var talkId: Int
    var onBack: (Screen) -> Unit
}


fun RBuilder.detail(talkId: Int, onBack: (Screen) -> Unit) = child(DetailScreen::class) {
    attrs.talkId = talkId
    attrs.onBack = onBack
}