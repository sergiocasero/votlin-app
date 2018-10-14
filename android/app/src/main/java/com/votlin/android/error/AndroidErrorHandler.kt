package com.votlin.android.error

import com.votlin.client.domain.error.Error
import com.votlin.client.domain.error.ErrorHandler
import android.content.Context
import com.votlin.android.R

class AndroidErrorHandler: ErrorHandler {

    override fun convert(error: Error): String =
            when (error) {
                //else -> context.getString(R.string.default_error)
                else -> ""
            }

}