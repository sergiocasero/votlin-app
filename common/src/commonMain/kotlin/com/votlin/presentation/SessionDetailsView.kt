package com.votlin.presentation

import com.votlin.data.SessionRating
import com.votlin.model.SessionModel
import com.votlin.presentation.BaseView

interface SessionDetailsView : BaseView {
    fun updateView(isFavorite: Boolean, session: SessionModel)
    fun setupRatingButtons(rating: SessionRating?)
    fun setRatingClickable(clickable: Boolean)
}