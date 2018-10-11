package com.votlin.backend

import io.ktor.application.Application
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

val comeBackLater = HttpStatusCode(477, "Come Back Later")
val tooLate = HttpStatusCode(478, "Too Late")
val keynoteTimeZone = ZoneId.of("Europe/Paris")!!
val keynoteEndDateTime = ZonedDateTime.of(
        2018, 10, 4, 10, 0, 0, 0, keynoteTimeZone
)!!

const val fakeSessionId = "007"

fun Application.launchSyncJob(sessionizeUrl: String, sessionizeInterval: Long) {
    log.info("Synchronizing each $sessionizeInterval minutes with $sessionizeUrl")
    launch(CommonPool) {
        while (true) {
            log.trace("Synchronizing to Sessionize…")
            log.trace("Finished loading data from Sessionize.")
            delay(sessionizeInterval, TimeUnit.MINUTES)
        }
    }
}

private val client = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer().apply {
        }
    }
}