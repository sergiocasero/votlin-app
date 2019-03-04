package com.votlin.common.model

sealed class Error {
    object NetworkError : Error()
}