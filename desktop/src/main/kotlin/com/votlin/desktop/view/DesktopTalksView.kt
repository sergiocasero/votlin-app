package com.votlin.desktop.view

import com.votlin.client.presentation.TalksListPresenter
import com.votlin.client.presentation.TalksListView
import com.votlin.desktop.di.errorHandler
import com.votlin.desktop.di.executor
import com.votlin.desktop.di.repository
import com.votlin.model.Talk
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Parent
import tornadofx.*

class DesktopTalksView : View("Votlin"), TalksListView {

    private val progressProperty = SimpleBooleanProperty()

    private val talksModel = observableList<Talk>()

    private val presenter = TalksListPresenter(
        executor = executor,
        repository = repository,
        errorHandler = errorHandler,
        view = this
    )

    override fun onDock() {
        super.onDock()
        presenter.initialize()
    }

    override fun onUndock() {
        super.onUndock()
        presenter.destroy()
    }

    override val root: Parent = stackpane {
        progressbar { visibleWhen { progressProperty } }
        tableview(talksModel) {
            readonlyColumn("Name", Talk::name)
            readonlyColumn("description", Talk::description)
                .pctWidth(80)
            readonlyColumn("track", Talk::track)
            smartResize()

            onUserSelect { presenter.onTalkClicked(it) }
        }
    }

    override fun showTalks(talks: List<Talk>) {
        talksModel.addAll(talks)
    }

    override fun goToTalkScreen(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        progressProperty.value = true
    }

    override fun hideProgress() {
        progressProperty.value = false
    }

    override fun showError(error: String) {
        println("showError: $error")
    }

    override fun showMessage(message: String) {
        println("showMessage: $message")
    }
}