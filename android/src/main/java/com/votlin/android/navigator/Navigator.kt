package com.votlin.android.navigator

import android.content.Context
import com.votlin.android.ui.activity.DetailActivity
import com.votlin.android.ui.activity.TalksActivity
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri


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

fun openUri(context: Context, url : String){
    val intent= Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}