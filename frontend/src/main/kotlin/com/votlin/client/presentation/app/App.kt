package com.votlin.client.presentation.app

import com.votlin.client.presentation.navigator.Screen
import com.votlin.client.presentation.screen.detail
import com.votlin.client.presentation.screen.home
import com.votlin.client.presentation.screen.splash
import react.*
import react.dom.div

abstract class App : RComponent<RProps, AppState>() {

    init {
        state = AppState()
    }

    override fun RBuilder.render() {
        div("app") {
            when (state.screen) {
                Screen.SPLASH -> splash { setState { screen = it } }
                Screen.HOME -> home {
                    setState {
                        talkId = it
                        screen = Screen.DETAIL
                    }
                }
                Screen.DETAIL -> detail(state.talkId) { setState { screen = it } }
            }
        }
    }
}

class AppState : RState {
    var screen: Screen = Screen.SPLASH
    var talkId: Int = 0
}

fun RBuilder.app() = child(App::class) {}