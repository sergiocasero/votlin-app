package com.votlin.android.navigator

import android.content.Context
import com.votlin.android.ui.TalksActivity

/**
 * Navigator.
 */
fun navigateToTalksActivity(context: Context) {
    val intent = TalksActivity.getCallingIntent(context)
    context.startActivity(intent)
}