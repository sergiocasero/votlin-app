package com.votlin.presentation


interface SearchQueryProvider {
    val searchQuery: String
    fun addOnQueryChangedListener(listener: (String) -> Unit)
}