package com.votlin.model

import kotlinx.serialization.Serializable

@Serializable
data class Time(val start: Long,
                val end: Long)

data class Talk(val id: Int,
                val name: String,
                val description: String,
                val speakers: List<Speaker>,
                val track: Track,
                val time: Time)

data class TalksResponse(val talks: List<Talk>)

enum class Track {
    BUSINESS, DEVELOPMENT, MAKER, ALL
}

@Serializable
data class Speaker(
        val twitter: String,
        val linkedin: String,
        val name: String,
        val bio: String,
        val photoUrl: String)

@Serializable
data class Rate(val id: Int, val value: Int)


@Serializable
internal data class TalkDto(
        val id: Int,
        val name: String,
        val description: String,
        val speakers: List<Speaker>,
        val track: String,
        val time: Time)

internal fun TalkDto.toModel(): Talk = Talk(
        id = id,
        name = name,
        speakers = speakers,
        time = time,
        track = Track.valueOf(track),
        description = description
)