package com.votlin.backend

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
            get { call.respond(getTalks()) }
            get("/business") { call.respond(getTrackTalks(Track.BUSINESS)) }
            get("/development") { call.respond(getTrackTalks(Track.DEVELOPMENT)) }
            get("/maker") { call.respond(getTrackTalks(Track.MAKER)) }
            get("/{id}") {
                call.parameters["id"]?.toIntOrNull()?.let { id -> call.respond(getTalkById(id = id)) }
            }

            // POST
            post("/rate") { call.respond(addTalkRating(call.receive())) }
        }
    }
}