package com.votlin.common.model

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable
data class Time(
    val start: Long,
    val end: Long
)

@Serializable
data class Talk(
    val id: Int,
    val name: String,
    val description: String,
    val speakers: List<Speaker>,
    @Serializable(TrackSerializer::class) val track: Track,
    val time: Time
)

@Serializable
data class TalksResponse(val talks: List<Talk>)

@Serializer(forClass = Track::class)
object TrackSerializer : KSerializer<Track> {

    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(input: Decoder): Track {
        return Track.valueOf(input.decodeString())
    }

    override fun serialize(output: Encoder, obj: Track) {
        output.encodeString(value = obj.toString())
    }

}

enum class Track {
    BUSINESS, DEVELOPMENT, MAKER, ALL
}

@Serializable
data class Speaker(
    val twitter: String,
    val linkedin: String,
    val name: String,
    val bio: String,
    val photoUrl: String
)

@Serializable
data class Rate(val id: Int, val value: Int)