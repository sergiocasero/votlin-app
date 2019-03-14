package com.votlin.client.presentation.screen

import com.votlin.common.client.presentation.SplashPresenter
import com.votlin.common.client.presentation.SplashView
import com.votlin.client.presentation.di.errorHandler
import com.votlin.client.presentation.navigator.Screen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.html.id
import react.RBuilder
import react.RProps
import react.dom.div
import react.dom.img

class SplashScreen : RootScreen<SplashProps, SplashState, SplashView>(), SplashView {

    override val presenter: SplashPresenter = SplashPresenter(
            view = this,
            errorHandler = errorHandler)

    override fun RBuilder.render() {
        div("splash") {
            img {
                attrs.id = "logo"
                attrs.src = "http://sergiocasero.es/votlin_logo.png"
            }
            div("progress") {
                img("progress") {
                    attrs.src = "https://mir-s3-cdn-cf.behance.net/project_modules/disp/35771931234507.564a1d2403b3a.gif"
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

    override fun showLoadingProgress(delayMillis: Long) {

        GlobalScope.launch {
            delay(2000)
            presenter.onRunnableCallback()
        }
    }

    override fun goToTalksScreen() {
        this.props.onDownloadFinished(Screen.HOME)
    }
}

interface SplashState : ScreenState

interface SplashProps : RProps {
    var onDownloadFinished: (Screen) -> Unit
}

fun RBuilder.splash(downloadFinished: (Screen) -> Unit) = child(SplashScreen::class) {
    attrs.onDownloadFinished = downloadFinished
}