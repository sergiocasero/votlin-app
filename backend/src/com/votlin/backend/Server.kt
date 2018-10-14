package com.votlin.backend

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, 10000) {

        // Serialize json
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        // Return custom errors (if needed)
        install(StatusPages)

        // Modules
        talksModule()
    }.start(wait = true)
}