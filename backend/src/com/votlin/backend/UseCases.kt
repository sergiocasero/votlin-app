package com.votlin.backend

import com.votlin.common.model.Rate
import com.votlin.common.model.Speaker
import com.votlin.common.model.Talk
import com.votlin.common.model.Track
import io.ktor.http.HttpStatusCode
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun getTalkById(id: Int): Talk
    = transaction { TalkVo.select { TalkVo.id eq id }.first() }.toTalk(getTalkSpeakers(talkId = id))


fun getTalks(): List<Talk> = transaction { TalkVo.selectAll().toList() }.map {
    it.toTalk(getTalkSpeakers(it[TalkVo.id]))
}

fun getTalkRating(talkId: Int): Double {
    val values = transaction { RateVo.select { RateVo.talkId eq talkId }.toList() }
            .sumBy { result -> result[RateVo.value] }

    val count = transaction { RateVo.select { RateVo.talkId eq talkId }.toList() }.count()
    return (values / count).toDouble()
}

fun getTalkSpeakers(talkId: Int): List<Speaker> {
    val speakerIds = transaction { TalkSpeakerVo.select { TalkSpeakerVo.talkId eq talkId }.toList() }
            .map { row -> row[TalkSpeakerVo.speakerId] }

    return transaction { SpeakerVo.select { SpeakerVo.id inList speakerIds }.toList() }
            .map { row -> row.toSpeaker() }
}

fun getTrackTalks(track: Track): List<Talk> = getTalks().filter { it.track == track }

fun addTalkRating(rate: Rate): HttpStatusCode {
    transaction {
        RateVo.insert { rateVo ->
            rateVo[talkId] = rate.id
            rateVo[value] = rate.value
        }
    }

    return HttpStatusCode.Created
}