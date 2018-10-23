package com.votlin.desktop.data

import com.votlin.client.data.datasource.local.LocalDataSource
import com.votlin.client.data.datasource.remote.RemoteDataSource
import com.votlin.model.Rate
import com.votlin.model.Talk

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

    override fun getRate(talkId: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}