package com.votlin.presentation

interface NavigationManager {
    fun showSessionList()
    fun showSessionDetails(sessionId: String)
    fun showPrivacyPolicyDialog()
}