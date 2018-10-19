package com.votlin.client.presentation.app

import com.votlin.client.presentation.navigator.Screen
import com.votlin.client.presentation.screen.home
import com.votlin.client.presentation.splash
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
                    println(it)
                }
            }
        }
        // when (state.track) {
        //     Track.BUSINESS -> trackList(Track.BUSINESS)
        //     Track.DEVELOPMENT -> trackList(Track.DEVELOPMENT)
        //     Track.MAKER -> trackList(Track.MAKER)
        //     Track.ALL -> trackList(Track.ALL)
        // }
    }
}

class AppState : RState {
    var screen: Screen = Screen.SPLASH
}

fun RBuilder.app() = child(App::class) {}