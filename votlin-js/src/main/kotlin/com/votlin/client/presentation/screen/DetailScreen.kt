package com.votlin.client.presentation.screen

import com.votlin.client.presentation.*
import com.votlin.model.Talk
import react.RBuilder
import react.RProps
import react.dom.div
import react.setState

class DetailScreen : RootScreen<DetailProps, DetailState, DetailView>(), DetailView {
    override val presenter: Presenter<DetailView> = DetailPresenter(
            repository = repository,
            executor = executor,
            errorHandler = errorHandler,
            view = this
    )

    override fun RBuilder.render() {
        if (state.talk != undefined) {
            div { +state.talk.name }
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
}

interface DetailState : ScreenState {
    var talk: Talk
}

interface DetailProps : RProps {
    var talkId: Int
}


fun RBuilder.detail(talkId: Int) = child(DetailScreen::class) {
    attrs.talkId = talkId
}