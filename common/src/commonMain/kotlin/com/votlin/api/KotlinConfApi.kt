package com.votlin.api

import com.votlin.data.AllData
import com.votlin.data.Favorite
import com.votlin.data.Vote
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.response.HttpResponse
import io.ktor.http.*
import kotlinx.io.core.use

class KotlinConfApi(private val endPoint: String, private val userId: String) {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
            }
        }
        install(ExpectSuccess)
    }

    suspend fun createUser(userId: String): Boolean = client.request<HttpResponse> {
        apiUrl("users")
        method = HttpMethod.Post
        body = userId
    }.use {
        it.status.isSuccess()
    }

    suspend fun getAll(userId: String?): AllData = client.get {
        apiUrl("all", userId)
    }

    suspend fun postFavorite(favorite: Favorite, userId: String): Unit = client.post {
        apiUrl("favorites", userId)
        json()
        body = favorite
    }

    suspend fun deleteFavorite(favorite: Favorite, userId: String): Unit = client.delete {
        apiUrl("favorites", userId)
        json()
        body = favorite
    }

    suspend fun postVote(vote: Vote, userId: String): Unit = client.post {
        apiUrl("votes", userId)
        json()
        body = vote
    }

    suspend fun deleteVote(vote: Vote, userId: String): Unit = client.delete {
        apiUrl("votes", userId)
        json()
        body = vote
    }

    private fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }

    private fun HttpRequestBuilder.apiUrl(path: String, userId: String? = null) {
        if (userId != null) {
            header(HttpHeaders.Authorization, "Bearer $userId")
        }
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}
