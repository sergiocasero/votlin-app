package com.votlin.desktop.view

import com.votlin.client.presentation.DetailPresenter
import com.votlin.client.presentation.DetailView
import com.votlin.desktop.app.Styles
import com.votlin.desktop.di.errorHandler
import com.votlin.desktop.di.executor
import com.votlin.desktop.di.repository
import com.votlin.model.Talk
import javafx.beans.property.SimpleBooleanProperty
import tornadofx.*

class MainView : View("Votlin"), DetailView {

    private val presenter = DetailPresenter(
            executor = executor,
            errorHandler = errorHandler,
            repository = repository,
            view = this
    )

    private val progressProperty = SimpleBooleanProperty()

    private var progress by progressProperty

    private val talk: TalkModel by inject()

    override fun getTalkId(): Int = 100

    override fun showTalk(talk: Talk) {
        this.talk.name.value = talk.name
        this.talk.description.value = talk.description
    }

    override fun navigateToList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRate(rate: Int) {
        // TODO
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

    override val root = vbox {
        text(talk.name) {
            addClass(Styles.heading)
        }
        text(talk.description) {
            addClass(Styles.label)
        }
    }

    override fun onDock() {
        presenter.initialize()
        println("Hello tornado!")
    }
}

class TalkModel2 : ItemViewModel<Talk>() {
    val name = bind(Talk::name)
    val description = bind(Talk::description)
}