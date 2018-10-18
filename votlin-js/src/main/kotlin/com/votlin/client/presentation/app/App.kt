package com.votlin.client.presentation.app

import com.votlin.client.presentation.navigator.Screen
import com.votlin.client.presentation.screen.home
import com.votlin.client.presentation.splash
import react.*
import react.dom.div

abstract class App : RComponent<RProps, AppState>() {

    init {
        state.screen = Screen.SPLASH
    }

    override fun RBuilder.render() {
        div("app") {
            when (state.screen) {
                Screen.SPLASH -> splash { setState { screen = it } }
                Screen.HOME -> home()
            }
        }
    }
}

interface AppState : RState {
    var screen: Screen
}

fun RBuilder.app() = child(App::class) {}