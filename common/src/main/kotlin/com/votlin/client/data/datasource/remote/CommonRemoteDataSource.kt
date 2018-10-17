package com.votlin.client.data.datasource.remote

import com.votlin.model.Rate
import com.votlin.model.Talk
import com.votlin.model.Track

class CommonRemoteDataSource() : RemoteDataSource {
    override fun getTalks(): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTalk(talkId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackTalks(track: Track): List<Talk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rateTalk(rate: Rate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}