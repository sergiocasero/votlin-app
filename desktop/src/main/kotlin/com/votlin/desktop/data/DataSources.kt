package com.votlin.desktop.data

import com.votlin.common.client.data.datasource.local.LocalDataSource
import com.votlin.common.model.Rate
import com.votlin.common.model.Talk

class DesktopLocalDataSource : LocalDataSource {
    override fun getFavoriteTalks(): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveRate(rate: Rate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTalk(talk: Talk) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRate(talkId: Int): Int = 5

}