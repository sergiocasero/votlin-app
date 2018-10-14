package com.votlin.backend

import com.votlin.model.Talk
import com.votlin.model.Track
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                val client = HttpClient(Apache)

                val table = client.get<String>("https://www.extremaduradigitalday.com/programacion/")
                        .split("<table class=\"wcpt-schedule\" border=\"0\">")[1]
                        .split("</table>")[0]

                val talksContainer = table
                        .split("<tbody>")[1]
                        .split("</tbody")[0]

                talksContainer.toTalks()

                call.respondText("Hello")
            }
        }
    }.start(wait = true)
}

private fun String.toTalks(): List<Talk> {

    val lines = this.split("</tr>")

    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")
    println("---------")

    lines.forEach { line ->
        val timesString = line
                .split("<td class=\"wcpt-time\">")

        if (timesString.size > 1) {
            val dateTimes = timesString[1]
                    .split("</td>")[0]
                    .replace(" ", "")
                    .split("-")

            println(dateTimes)
            val businessArray = line
                    .split("<td class=\"wcb-speaker \"")

            println(businessArray)

//            if (businessArray.size > 1) {
//                val firstBusiness = businessArray[1].split("</td>")[0]
//
//                println(getTalk(firstBusiness, dateTimes, Track.Business("Room 1")))
//            }
//
//            if (businessArray.size > 2) {
//                val secondBusiness = businessArray[2].split("</td>")[0]
//
//                println(getTalk(secondBusiness, dateTimes, Track.Business("Room 2")))
//            }

        }
    }

    return listOf()
}

private fun getTalk(line: String, dateTimes: List<String>, track: Track): Talk {
    if (line.contains("href")) {
        // TODO get speaker details here
        val speakerUrl = line.split("href=\"")[1].split("/\"")[0]
    }

    println("---------")
    println(line)

    //val name = line.split("\">")[1].split("</a>")[0]
    //val speakerName = line.split("\"wcpt-session-speakers\">")[1].split("</span>")[0]
//
    //println("Start date: ${dateTimes[0]}, End date: ${dateTimes[1]}, name: $name, Speaker: $speakerName")
//
    //val speaker = Speaker(name = speakerName,
    //        twitter = "",
    //        photoUrl = "",
    //        linkedin = "",
    //        bio = "")

    return Talk(id = 0,
            datetimeStart = 0,
            datetimeEnd = 0,
            name = "",
            speakers = listOf(),
            description = "",
            track = track)
}