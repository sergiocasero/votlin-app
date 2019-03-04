package com.votlin.common.client.domain.error

interface ErrorHandler {
    fun convert(error: Error): String
}