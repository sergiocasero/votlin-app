package com.votlin.desktop.app

import com.votlin.desktop.view.DesktopTalksView
import javafx.stage.Stage
import tornadofx.App

class MyApp : App(DesktopTalksView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = true
    }
}