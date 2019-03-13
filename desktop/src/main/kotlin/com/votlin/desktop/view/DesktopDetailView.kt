package com.votlin.desktop.view

import com.votlin.client.presentation.DetailPresenter
import com.votlin.client.presentation.DetailView
import com.votlin.desktop.app.Styles
import com.votlin.desktop.di.errorHandler
import com.votlin.desktop.di.executor
import com.votlin.desktop.di.repository
import com.votlin.common.model.Talk
import javafx.scene.Parent
import tornadofx.*

class DesktopDetailView : View("Votlin"), DetailView {

    private val talkModel: TalkModel by inject()

    private val presenter = DetailPresenter(
            executor = executor,
            errorHandler = errorHandler,
            repository = repository,
            view = this
    )

    override fun onDock() {
        super.onDock()
        presenter.initialize()
    }

    override val root: Parent = vbox {
        label(talkModel.name) { addClass(Styles.heading) }
        label(talkModel.description) { addClass(Styles.label) }
    }

    override fun getTalkId(): Int = 101

    override fun showTalk(talk: Talk) {
        talkModel.name.value = talk.name
        talkModel.description.value = talk.description
    }

    override fun navigateToList() {
        // TODO
    }

    override fun showRate(rate: Int) {
        // TODO
    }

    override fun showProgress() {
        println("showProgress")
    }

    override fun hideProgress() {
        println("hideProgress")
    }

    override fun showError(error: String) {
        println("showError $error")
    }

    override fun showMessage(message: String) {
        println("showMessgae $message")
    }
}

class TalkModel : ItemViewModel<Talk>() {
    val name = bind(Talk::name)
    val description = bind(Talk::description)
}