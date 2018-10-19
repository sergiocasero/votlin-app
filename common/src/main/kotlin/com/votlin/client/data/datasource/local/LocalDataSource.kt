package com.votlin.client.data.datasource.local

import com.votlin.model.Rate
import com.votlin.model.Talk

interface LocalDataSource {
    fun getFavoriteTalks(): List<Talk>
    fun saveRate(rate: Rate)
    fun saveTalk(talk: Talk)
}