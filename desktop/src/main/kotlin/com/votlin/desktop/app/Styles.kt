package com.votlin.desktop.app

import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val label by cssclass()
    }

    init {
        heading {
            padding = box(10.px)
            fontSize = 40.px
            fontWeight = FontWeight.BOLD
            textAlignment = TextAlignment.CENTER
        }

        label {
            padding = box(15.px)
        }
    }
}