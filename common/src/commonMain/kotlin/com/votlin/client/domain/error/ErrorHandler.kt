package com.votlin.client.domain.error

interface ErrorHandler {
    fun convert(error: Error): String
}