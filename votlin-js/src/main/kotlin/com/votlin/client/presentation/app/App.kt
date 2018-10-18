package com.votlin.client.presentation.app

import com.votlin.client.presentation.navigator.Screen
import com.votlin.client.presentation.splash
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

abstract class App : RComponent<RProps, AppState>() {

    init {
        state.screen = Screen.SPLASH
    }

    override fun RBuilder.render() {
        div("app") {
            splash {
                div { +"Finished!" }
            }
        }
    }
}

interface AppState : RState {
    var screen: Screen
}

fun RBuilder.app() = child(App::class) {}