package com.votlin.client.data

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk
import kotlin.browser.localStorage


class JsLocalDataSource : LocalDataSource {
    override fun getFavoriteTalks(): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRate(rate: Rate) {
        localStorage.setItem("rate${rate.id}", rate.value.toString())
    }

    override fun saveTalk(talk: Talk) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRate(talkId: Int): Int = localStorage.getItem("rate$talkId")?.toIntOrNull() ?: 0
}