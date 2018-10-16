package com.votlin.backend

import com.votlin.model.Talk
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun getTalkById(id: Int): Talk {
    val result = transaction { TalkDb.select { TalkDb.id eq id }.first() }

    val speakerIds = transaction { TalkSpeaker.select { TalkSpeaker.talkId eq id }.toList() }
            .map { row -> row[TalkSpeaker.speakerId] }

    val speakers = transaction { SpeakerDb.select { SpeakerDb.id inList speakerIds }.toList() }
            .map { row -> row.toSpeaker() }

    return result.toTalk(id, speakers)
}