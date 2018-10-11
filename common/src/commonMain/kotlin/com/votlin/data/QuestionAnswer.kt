package com.votlin.data

import kotlinx.serialization.*

@Serializable
data class QuestionAnswer(
    val questionId: Int,
    val answerValue: String
)
