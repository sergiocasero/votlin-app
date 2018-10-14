package com.votlin.model

data class Time(val start: Long,
                val end: Long)

data class Talk(val id: Int,
                val name: String,
                val description: String,
                val speakers: List<Speaker>,
                val track: Track,
                val time: Time)

sealed class Track {
    data class Business(val room: String) : Track()
    data class Development(val room: String) : Track()
    data class Maker(val room: String) : Track()
}

data class Speaker(
        val twitter: String,
        val linkedin: String,
        val name: String,
        val bio: String,
        val photoUrl: String)