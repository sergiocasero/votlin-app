package com.votlin.desktop.app

import com.votlin.client.presentation.DetailView
import com.votlin.desktop.view.DesktopDetailView
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(DesktopDetailView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = 800.0
        stage.height = 600.0
    }
}