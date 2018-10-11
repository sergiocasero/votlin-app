package com.votlin.data

import kotlinx.serialization.*

@Serializable
data class Favorite(
    var sessionId: String
)