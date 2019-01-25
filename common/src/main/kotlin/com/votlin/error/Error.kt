package com.votlin.error

sealed class Error {
    object NetworkError : Error()
}