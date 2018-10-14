package com.votlin.presentation

//data class Shared(val text: String = "HELLO ${Platform.name}! :)")
class Shared(
        private val view: PresenterView,
        private val platform: String
) {

    fun doShomething(){
        view.showText("Hello $platform!")
    }

}

//expect object Platform {
//    val name: String
//}
