package com.votlin.backend

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    embeddedServer(Netty, 10000) {

        // Database
        Database.connect(url = "jdbc:mysql://localhost:3306/edd",
                driver = "com.mysql.jdbc.Driver",
                user = "edd",
                password = "extremaduradigitalday")

        // Serialize json
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        // Return custom errors (if needed)
        install(StatusPages)
        install(CORS) { anyHost() }

        // Modules
        main()
    }.start(wait = true)
}