package com.votlin.model

import com.votlin.api.KotlinConfApi
import com.votlin.storage.Settings

class KotlinConfDataRepository(
        endPoint: String,
        uid: String,
        private val settings: Settings
) {
    private val api = KotlinConfApi(endPoint, uid)


}