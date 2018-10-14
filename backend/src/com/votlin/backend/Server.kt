package com.votlin.backend

import com.fasterxml.jackson.databind.SerializationFeature
import com.google.gson.Gson
import com.votlin.model.TalksResponse
import com.votlin.model.Track
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        install(StatusPages)
        routing {
            route("/talk") {
                val talks = getTalksFile().talks
                get {
                    call.respond(talks)
                }
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
                post("/rate") {  }
            }
        }
    }.start(wait = true)
}

private fun getTalksFile(): TalksResponse =
        Gson().fromJson(
                File("resources/schedule.json").inputStream().bufferedReader().use { line -> line.readText() },
                TalksResponse::class.java
        )