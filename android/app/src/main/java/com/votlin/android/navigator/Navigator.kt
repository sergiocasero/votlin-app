package com.votlin.android.navigator

import android.content.Context
import com.votlin.android.ui.activity.DetailActivity
import com.votlin.android.ui.activity.TalksActivity

/**
 * Navigator.
 */
fun navigateToTalksActivity(context: Context) {
    val intent = TalksActivity.getCallingIntent(context)
    context.startActivity(intent)
}

fun navigateToTalkDetailActivity(context: Context, talkId: Int) {
    val intent = DetailActivity.getCallingIntent(context, talkId)
    context.startActivity(intent)
}