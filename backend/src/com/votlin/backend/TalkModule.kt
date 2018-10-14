package com.votlin.backend

import com.google.gson.Gson
import com.votlin.model.Rate
import com.votlin.model.TalksResponse
import com.votlin.model.Track
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import java.io.File

fun Application.talksModule() {
    routing {
        route("/talk") {
            val talks = getTalksFile().talks
            get { call.respond(talks) }
            get("/{id}") {
                val id = call.parameters["id"]

                if (id?.toIntOrNull() != null) {
                    val talk = talks.firstOrNull { talk -> talk.id == id.toInt() }

                    if (talk != null) {
                        call.respond(talk)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("/business") { call.respond(TalksResponse(talks.filter { talk -> talk.track == Track.BUSINESS })) }
            get("/development") { call.respond(TalksResponse(talks.filter { talk -> talk.track == Track.DEVELOPMENT })) }
            get("/maker") { call.respond(TalksResponse(talks.filter { talk -> talk.track == Track.MAKER })) }
            post("/rate") {
                val rate = call.receive<Rate>()
                call.respond(rate)
            }
        }
    }
}

private fun getTalksFile(): TalksResponse =
        Gson().fromJson(
                File("resources/schedule.json").inputStream().bufferedReader().use { line -> line.readText() },
                TalksResponse::class.java
        )