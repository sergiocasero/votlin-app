package com.votlin.android.api

import com.votlin.client.data.datasource.remote.CommonRemoteDataSource
import com.votlin.model.*
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

class AndroidRemoteDataSource : CommonRemoteDataSource() {
    private val httpClientConfig: HttpClientConfig<*>.() -> Unit = {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(TalkDto::class, TalkDto.serializer())
                setMapper(TalksResponseDto::class, TalksResponseDto.serializer())
                setMapper(Speaker::class, Speaker.serializer())
                setMapper(Rate::class, Rate.serializer())
                setMapper(Time::class, Time.serializer())
            }
        }
        install(ExpectSuccess)
    }

    override suspend fun getTalk(talkId: Int): Talk {
        return super.getTalk(talkId)
    }

    override suspend fun getTalksByTrack(track: String): List<Talk> {
        return super.getTalksByTrack(track)
    }

    override suspend fun getTalks(): List<Talk> = client.get<TalksResponseDto> { apiUrl("talk") }.talks.map { it.toModel() }

    override val client = HttpClient(httpClientConfig)
}

@Serializable
data class TalksResponseDto(val talks: List<TalkDto>)

@Serializable
data class TalkDto(val id: Int,
                   val name: String,
                   val description: String,
                   val speakers: List<Speaker>,
                   val track: Track,
                   val time: Time)

fun TalkDto.toModel(): Talk = Talk(id, name, description, speakers, track, time)