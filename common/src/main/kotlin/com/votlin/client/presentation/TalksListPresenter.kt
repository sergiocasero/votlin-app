package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.model.Speaker
import com.votlin.model.Talk
import com.votlin.model.Time
import com.votlin.model.Track

class TalksListPresenter(view: TalksListView, errorHandler: ErrorHandler) :
        Presenter<TalksListView>(view = view, errorHandler = errorHandler) {

    var track: Track? = null

    override fun initialize() {
        track = view.getTrack()
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun onViewVisible() {
        val trackValue = track
        if (trackValue != null) {
            view.showProgress()
            //TODO: Execute use case
            view.showTalks(mockTalks().filter { trackValue == Track.ALL || trackValue == it.track })
            view.hideProgress()
        }
    }

    fun onTalkClicked(talk: Talk) = view.goToTalkScreen(talk.id)

    // TODO: remove this
    private fun mockTalks() = listOf(
            Talk(id = 0,
                    name = "Registro",
                    description = "",
                    speakers = listOf(),
                    track = Track.ALL,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Bienvenida",
                    description = "",
                    speakers = listOf(),
                    track = Track.ALL,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Tecnología y negocio: de una idea innovadora a un producto de éxito",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Joaquín Álviz Martín", "", "")
                    ),
                    track = Track.BUSINESS,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Robótica: experiencia y retos futuros",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Concepción Alicia Monje", "", "")
                    ),
                    track = Track.DEVELOPMENT,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "¿Qué es un fablab?",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Juan Carlos Cano", "", "")
                    ),
                    track = Track.MAKER,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Break",
                    description = "",
                    speakers = listOf(),
                    track = Track.ALL,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Adventuras y desventuras de una spin-off universitaria",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Fernández Sánchez Figueroa", "", "")
                    ),
                    track = Track.BUSINESS,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Hablando de Big Data",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Sandra Navarro Nieto", "", "")
                    ),
                    track = Track.BUSINESS,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Devops: orígnees, realidades y mitos",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Francisco Rodríguez Domínguez", "", "")
                    ),
                    track = Track.DEVELOPMENT,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Aplicaciones multiplataforma con apacha cordova",
                    description = "",
                    speakers = listOf(
                            Speaker("", "", "Domingo M. Solomando Chamizo", "", "")
                    ),
                    track = Track.DEVELOPMENT,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Charla: Presentación de asociaciones",
                    description = "",
                    speakers = listOf(),
                    track = Track.MAKER,
                    time = Time(1540573874942, 1540573874942)),
            Talk(id = 0,
                    name = "Break",
                    description = "",
                    speakers = listOf(),
                    track = Track.ALL,
                    time = Time(1540573874942, 1540573874942))
            )

}

interface TalksListView : Presenter.View {
    fun getTrack(): Track
    fun showTalks(talks: List<Talk>)
    fun goToTalkScreen(id: Int)
}