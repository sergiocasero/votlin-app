@file:Suppress("NestedLambdaShadowedImplicitParameter")

package com.votlin.backend

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.principal
import io.ktor.features.origin
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.websocket.Frame
import io.ktor.pipeline.PipelineContext
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.websocket.webSocket
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.json.JSON
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ConcurrentHashMap

fun Routing.api(database: Database, production: Boolean, sessionizeUrl: String) {
    apiKeynote(production)
    apiUsers(database)
    apiAll(database)
    apiSession()
    apiVote(database, production)
    apiFavorite(database, production)
    apiSynchronize(sessionizeUrl)
    wsVotes(database, production)
}

/*
GET http://localhost:8080/keynote?datetimeoverride=2017-10-24T10:00-07:00
 */
fun Routing.apiKeynote(production: Boolean) {
    get("keynote") {
        val nowTime = simulatedTime(production)
        if (nowTime.isAfter(keynoteEndDateTime))
            call.respond(HttpStatusCode.OK)
        else {
            call.respond(comeBackLater)
        }
    }
}

private fun PipelineContext<Unit, ApplicationCall>.simulatedTime(production: Boolean): ZonedDateTime {
    val now = ZonedDateTime.now(keynoteTimeZone)
    return if (production)
        now
    else
        call.parameters["datetimeoverride"]?.let { ZonedDateTime.parse(it) } ?: now
}

/*
POST http://localhost:8080/user
1238476512873162837
 */
fun Routing.apiUsers(database: Database) {
    route("users") {
        post {
            val userUUID = call.receive<String>()
            val ip = call.request.origin.remoteHost
            val timestamp = LocalDateTime.now(Clock.systemUTC())
            val created = database.createUser(userUUID, ip, timestamp)
            if (created)
                call.respond(HttpStatusCode.Created)
            else
                call.respond(HttpStatusCode.Conflict)
        }
        get("count") {
            call.respondText(database.usersCount().toString())
        }
    }
}

suspend fun ApplicationCall.validatePrincipal(database: Database): KotlinConfPrincipal? {
    val principal = principal<KotlinConfPrincipal>() ?: return null
    if (!database.validateUser(principal.token)) return null
    return principal
}

/*
GET http://localhost:8080/favorites
Accept: application/json
Authorization: Bearer 1238476512873162837
*/
fun Routing.apiFavorite(database: Database, production: Boolean) {
    route("favorites") {
        get {
        }
        if (!production) {
            get("all") {
            }
        }
        post {
        }
        delete {
        }
    }
}

private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

/*
GET http://localhost:8080/votes
Accept: application/json
Authorization: Bearer 1238476512873162837
*/
fun Routing.apiVote(database: Database, production: Boolean) {
    route("votes") {
        get {

        }
        get("all") {
        }
        get("summary/{sessionId}") {
            val id = call.parameters["sessionId"] ?: throw BadRequest()
            val votesSummary = database.getVotesSummary(id)
            call.respond(votesSummary)
        }
        post {

        }
        delete {
        }
    }
}


/*
GET http://localhost:8080/all
Accept: application/json
Authorization: Bearer 1238476512873162837
*/
fun Routing.apiAll(database: Database) {
    get("all") {

    }
}

fun Routing.apiSession() {
    route("sessions") {
        get {

        }
        get("{sessionId}") {

        }
    }
}

// maps sessionId to the "session updated" signal (a signal is just Unit -- it carries no additional data).
val sessionSignals = ConcurrentHashMap<String, ConflatedBroadcastChannel<Unit>>()

fun signalSession(sessionId: String) =
        sessionSignals[sessionId]?.offer(Unit) // offer to anyone who's interested

fun trackSession(sessionId: String): ConflatedBroadcastChannel<Unit> =
        sessionSignals.computeIfAbsent(sessionId) { ConflatedBroadcastChannel(Unit) }

fun Routing.wsVotes(database: Database, production: Boolean) {
    val route = if (production) fakeSessionId else "{sessionId}"
    webSocket("sessions/$route/votes") {
        val id = call.parameters["sessionId"] ?: fakeSessionId
        trackSession(id).openSubscription().consume {
            consumeEach {
                outgoing.send(Frame.Text(JSON.stringify(database.getVotesSummary(id))))
            }
        }
    }
}

/*
GET http://localhost:8080/sessionizeSync
*/
fun Routing.apiSynchronize(sessionizeUrl: String) {
    get("sessionizeSync") {
    }
}