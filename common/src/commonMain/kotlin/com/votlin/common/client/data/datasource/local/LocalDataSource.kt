package com.votlin.common.client.data.datasource.local

import com.votlin.common.model.Rate
import com.votlin.common.model.Talk

interface LocalDataSource {
    fun getFavoriteTalks(): List<Talk>
    fun saveRate(rate: Rate)
    fun saveTalk(talk: Talk)
    fun getRate(talkId: Int): Int
}