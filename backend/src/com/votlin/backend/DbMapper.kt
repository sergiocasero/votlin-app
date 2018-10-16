package com.votlin.backend

import com.votlin.model.Speaker
import com.votlin.model.Talk
import com.votlin.model.Time
import com.votlin.model.Track
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toTalk(id: Int, speakers: List<Speaker>): Talk = Talk(
        id = id,
        name = this[TalkDb.name],
        description = this[TalkDb.description],
        track = Track.valueOf(this[TalkDb.track]),
        time = Time(start = this[TalkDb.start], end = this[TalkDb.end]),
        speakers = speakers
)


fun ResultRow.toSpeaker(): Speaker = Speaker(
        name = this[SpeakerDb.name],
        bio = this[SpeakerDb.bio],
        twitter = this[SpeakerDb.twitter],
        linkedin = this[SpeakerDb.linkedin],
        photoUrl = this[SpeakerDb.photoUrl]
)