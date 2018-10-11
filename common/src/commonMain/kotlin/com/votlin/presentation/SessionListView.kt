package com.votlin.presentation

import com.votlin.model.SessionModel
import com.votlin.presentation.BaseView

interface SessionListView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(sessions: List<SessionModel>, favorites: List<SessionModel>)
}
