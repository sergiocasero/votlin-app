package com.votlin.backend

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TalkVo : Table("talk") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val description: Column<String> = text("description")
    val track: Column<String> = varchar("track", 11)
    val start: Column<Long> = long("start")
    val end: Column<Long> = long("end")
}

object SpeakerVo : Table("speaker") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val twitter: Column<String> = varchar("twitter", 60)
    val linkedin: Column<String> = varchar("linkedin", 150)
    val bio: Column<String> = text("bio")
    val photoUrl: Column<String> = varchar("photoUrl", 150)
}

object TalkSpeakerVo : Table("talk_speaker") {
    val talkId: Column<Int> = integer("talk_id")
    val speakerId: Column<Int> = integer("speaker_id")
}

object RateVo : Table("rate") {
    val talkId: Column<Int> = integer("talk_id")
    val value: Column<Int> = integer("value")
}