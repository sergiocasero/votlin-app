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
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

fun Application.main() {
    talks()
}

fun Application.talks() {
    routing {
        route("/talk") {
            val talks = getTalksFile().talks
            get { call.respond(talks) }
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()

                if (id != null) {
                    call.respond(getTalkById(id))
                    val result = transaction { TalkDb.select { TalkDb.id eq id }.firstOrNull() }

                    if (result != null) {

                        val speakerIds = transaction { TalkSpeaker.select { TalkSpeaker.talkId eq id }.toList() }
                                .map { row -> row[TalkSpeaker.speakerId] }

                        val speakers = transaction { SpeakerDb.select { SpeakerDb.id inList speakerIds }.toList() }
                                .map { row -> row.toSpeaker() }

                        val talk = result.toTalk(id, speakers)

                        call.respond(talk)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "talk with id $id not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "id must be Integer")
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