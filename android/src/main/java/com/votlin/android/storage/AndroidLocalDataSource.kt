package com.votlin.android.storage

import android.content.Context
import com.votlin.common.client.data.datasource.local.LocalDataSource
import com.votlin.common.model.Rate
import com.votlin.common.model.Talk

class AndroidLocalDataSource(context: Context) : LocalDataSource {

    companion object {
        const val RATE_PREFIX_KEY = "RATE_PREFIX_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences("votlin", Context.MODE_PRIVATE)


    override fun getFavoriteTalks(): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRate(rate: Rate) {
        sharedPreferences.edit()
                .putInt("$RATE_PREFIX_KEY${rate.id}", rate.value)
                .apply()
    }

    override fun saveTalk(talk: Talk) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRate(talkId: Int): Int = sharedPreferences.getInt("$RATE_PREFIX_KEY$talkId", 0)
}