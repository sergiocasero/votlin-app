package com.votlin.desktop.view

import com.votlin.client.presentation.TalksListPresenter
import com.votlin.client.presentation.TalksListView
import com.votlin.desktop.di.errorHandler
import com.votlin.desktop.di.executor
import com.votlin.desktop.di.repository
import com.votlin.model.Talk
import com.votlin.model.Track
import javafx.beans.property.SimpleBooleanProperty
import javafx.collections.FXCollections.observableArrayList
import javafx.scene.control.TabPane
import tornadofx.*

class MainView : View("Hello Votlin from TornadoFX! :)"), TalksListView {

    private val presenter = TalksListPresenter(
            executor = executor,
            errorHandler = errorHandler,
            repository = repository,
            view = this
    )

    private val progressProperty = SimpleBooleanProperty()

    private val talks = observableArrayList<Talk>()

    private var progress by progressProperty

    private var track = Track.ALL

    override fun getTrack(): Track = track

    override fun showTalks(talks: List<Talk>) {
        println(talks.toString())
    }

    override fun goToTalkScreen(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        println("hello show progress!")
        progress = true
    }

    override fun hideProgress() {
        println("hello hide progress!")
        progress = false
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab("All") {
            setOnMouseClicked {
                track = Track.ALL
                presenter.onViewVisible()
            }
            progressbar { removeWhen(progressProperty.not()) }
            listview(talks) {
                enableWhen(progressProperty.not())
                
            }
        }
        tab("Business") {
            setOnMouseClicked {
                track = Track.BUSINESS
                presenter.onViewVisible()
            }
        }
        tab("Development") {
            setOnMouseClicked {
                track = Track.DEVELOPMENT
                presenter.onViewVisible()
            }
        }
        tab("Maker") {
            setOnMouseClicked {
                track = Track.MAKER
                presenter.onViewVisible()
            }
        }
    }

    override fun onDock() {
        presenter.initialize()
        println("Hello tornado!")
    }
}