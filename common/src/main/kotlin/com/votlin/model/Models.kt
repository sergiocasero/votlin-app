package com.votlin.model

import kotlinx.serialization.Serializable

@Serializable
data class Time(val start: Long,
                val end: Long)

@Serializable
data class Talk(val id: Int,
                val name: String,
                val description: String,
                val speakers: List<Speaker>,
                val track: Track,
                val time: Time)

@Serializable
data class TalksResponse(val talks: List<Talk>)

@Serializable
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