package com.votlin.client.presentation

import react.RBuilder
import react.RProps
import react.dom.div

class SplashScreen : RootScreen<SplashProps, SplashState, SplashView>(), SplashView {

    override val presenter: SplashPresenter = SplashPresenter(
            view = this,
            errorHandler = errorHandler)

    override fun RBuilder.render() {
        div("splash") {
            +"Hello from splash screen"
        }
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showMessage(message: String) {
        println(message)
    }

    override fun showLoadingProgress(delayMillis: Long) {
        println("Progress!!")
        presenter.onRunnableCallback()
    }

    override fun goToTalksScreen() {
        this.props.downloadFinished()
    }
}

interface SplashState : ScreenState

interface SplashProps : RProps {
    var downloadFinished: () -> Unit
}

fun RBuilder.splash(downloadFinished: () -> Unit) = child(SplashScreen::class) {
    attrs.downloadFinished = downloadFinished
}