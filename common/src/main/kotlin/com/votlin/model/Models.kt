package com.votlin.model

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

data class Speaker(
        val twitter: String,
        val linkedin: String,
        val name: String,
        val bio: String,
        val photoUrl: String)

data class Rate(val id: Int, val value: Int)