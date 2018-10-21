package com.votlin.backend

import com.votlin.model.TalksResponse
import com.votlin.model.Track
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.main() {
    talks()
}

fun Application.talks() {
    routing {
        route("/talk") {
            //GET
            val allTalks = getTalks()
            val businessTalks = getTrackTalks(Track.BUSINESS)
            val devTalks = getTrackTalks(Track.DEVELOPMENT)
            val makerTalks = getTrackTalks(Track.MAKER)

            get { call.respond(TalksResponse(allTalks)) }
            get("/business") { call.respond(TalksResponse(businessTalks)) }
            get("/development") { call.respond(TalksResponse(devTalks)) }
            get("/maker") { call.respond(TalksResponse(makerTalks)) }
            get("/{id}") {
                call.parameters["id"]?.toIntOrNull()?.let { id -> call.respond(getTalkById(id = id)) }
            }

            // POST
            post("/rate") { call.respond(addTalkRating(call.receive())) }
        }
    }
}