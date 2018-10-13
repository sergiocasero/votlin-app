package com.votlin.presentation

data class Shared(val text: String = "HELLO ${Platform.name}! :)")

expect object Platform {
    val name: String
}