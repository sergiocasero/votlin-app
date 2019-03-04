package com.votlin.android.error

import com.votlin.common.client.domain.error.Error
import com.votlin.common.client.domain.error.ErrorHandler

class AndroidErrorHandler: ErrorHandler {

    override fun convert(error: Error): String =
            when (error) {
                //else -> context.getString(R.string.default_error)
                else -> ""
            }

}