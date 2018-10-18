package com.votlin.client.presentation.error

import com.votlin.client.domain.error.Error
import com.votlin.client.domain.error.ErrorHandler

class JsErrorHandler : ErrorHandler {
    override fun convert(error: Error): String {
        return ""
    }
}